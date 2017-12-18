package com.cn.cms.biz;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.parser.Feature;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.cn.cms.bo.ColumnBean;
import com.cn.cms.bo.PermissionBean;
import com.cn.cms.contants.RedisKeyContants;
import com.cn.cms.contants.StaticContants;
import com.cn.cms.enums.ErrorCodeEnum;
import com.cn.cms.enums.PermissionTypeEnum;
import com.cn.cms.enums.PlatformEnum;
import com.cn.cms.enums.ShowFlagEnum;

import com.cn.cms.middleware.JedisClient;
import com.cn.cms.po.Permission;
import com.cn.cms.po.PermissionUser;
import com.cn.cms.service.ColumnService;
import com.cn.cms.service.UserService;
import com.cn.cms.utils.Page;
import com.cn.cms.utils.StringUtils;
import com.cn.cms.web.result.ApiResponse;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/11/17 0017.
 */

@Component
public class PermissionBiz extends BaseBiz {

    @Resource
    private UserService userService;

    @Resource
    private JedisClient jedisClient;


    /**
     * 获取用户菜单权限
     * @param userId
     * @return
     */
    public List<PermissionBean> getMenuPermission(String userId){
        String result = jedisClient.get(RedisKeyContants.getMenuPermission(userId));
        if(StringUtils.isNotBlank(result)) {
            return JSONArray.parseArray(result, PermissionBean.class);
        }
        return null;
    }

    /**
     * 根据用户ID 删除权限
     * @param userId
     */
    public void delPermissionRedis(String userId){
        jedisClient.del(RedisKeyContants.getPermission(userId));
        jedisClient.del(RedisKeyContants.getMenuPermission(userId));
    }
    public void delAppPermissionRedis(String userId){
        jedisClient.del(RedisKeyContants.getAppPermission(userId));
        jedisClient.del(RedisKeyContants.getAppMenuPermission(userId));
    }

    /**
     * 获取栏目名称是否存在
     * @param parentId
     * @param name
     * @return
     */
    public Integer queryPermissionName(Integer parentId , String name){
        return userService.queryPermissionName(parentId,name);
    }

    public boolean checkPermission(String userId, String permission){
        Long result = jedisClient.zrank(RedisKeyContants.getPermission(userId), permission);
        if(result!=null && result > 0){
            return true;
        }
        return false;
    }

    /**
     * 获取栏目信息
     * @param id
     * @return
     */
    public Permission getPermissionColumn(Integer id){
        return userService.queryPermissionColumn(id);
    }

    /**
     * 栏目列表
     * @param permissions
     * @return
     */
    public List<PermissionBean> getColumn(List<Permission> permissions){

        List<PermissionBean> columnBeans = new ArrayList<>();
        List<Permission> catchList = new ArrayList<>();

        for(int i = 0; i < permissions.size(); i++) {
            Permission permission = permissions.get(i);
            if (permission.getParentId() == null) {
                PermissionBean permissionBean = new PermissionBean();
                permissionBean.setPermission(permission);
                columnBeans.add(permissionBean);
            } else {
                catchList.add(permission);
            }
        }

        List<PermissionBean> result = setColumn(columnBeans, catchList);

        return result;
    }

    /**
     * 解析列表，分类相应栏目
     * @param parentBeans  父list
     * @param allList   所有 list
     * @return
     */
    private List<PermissionBean> setColumn(List<PermissionBean> parentBeans,List<Permission> allList){

        for(int i=0;i<parentBeans.size();i++){
            PermissionBean parent = parentBeans.get(i);
            Permission parentPermission = parent.getPermission();

            //获取子类
            List<Permission> firstChild = new ArrayList<>();
            List<PermissionBean> list = new ArrayList<>();

            for(int j=0;j<allList.size();j++){
                Permission child = allList.get(j);
                if(child.getParentId() == parentPermission.getId()){
                    firstChild.add(child);
                }
            }

            for(int k=0;k<firstChild.size();k++){
                PermissionBean permissionBean = new PermissionBean();
                permissionBean.setPermission(firstChild.get(k));
                list.add(permissionBean);
            }
            if(list.size()>0){//(一级)子类获取(二级)子类
                setColumn(list,allList);
                parent.setPermissionBeans(list);
            }
        }
        return parentBeans;
    }


    public void setPermissionRedis(String userId, PlatformEnum platformEnum){
        List<Permission> permissions = userService.findPermissionColumn(userId,platformEnum.getType());
        if(StringUtils.isNotEmpty(permissions)){
            List<PermissionBean> permissionBeenList = getColumn(permissions);
            if(platformEnum.getType() == PlatformEnum.CMS.getType()) { //cms 为PC
                delPermissionRedis(userId);
                //获取MENU列表
                jedisClient.set(RedisKeyContants.getMenuPermission(userId), JSONArray.toJSONString(permissionBeenList), StaticContants.DEFAULT_SECONDS);

            }else if(platformEnum.getType() == PlatformEnum.APP.getType()){
                delAppPermissionRedis(userId);
                //获取MENU列表
                jedisClient.set(RedisKeyContants.getAppMenuPermission(userId), JSONArray.toJSONString(permissionBeenList), StaticContants.DEFAULT_SECONDS);
            }
        }
    }

    public void savePermissionColumn(String userID,String name,Integer parentId,String parentUrl , String url,Integer sort,String description){
        Permission permission = new Permission();
        PermissionUser permissionUser = new PermissionUser();

        permission.setName(name);
        permission.setUrl(url);
        permission.setLastModifyUserId(userID);
        permission.setCreateUserId(userID);
        if(StringUtils.isNotBlank(url)){
            if(StringUtils.isNotBlank(parentUrl)){
                permission.setUrl(parentUrl+url);
            }else{
                permission.setUrl(url);
            }
        }
        if(StringUtils.isNotBlank(description)){
            permission.setDescription(description);
        }
        if(parentId!=null){
            permission.setParentId(parentId);
        }
        if(sort!=null){
            permission.setSort(sort);
        }
        userService.savePermissionColumn(permission);
        permissionUser.setUserId(userID);
        permissionUser.setPositionId(permission.getId());
        userService.savePermissionColumnUser(permissionUser);

        //新增的栏目添加到redis中  转化较复杂
        /*String result = jedisClient.get(RedisKeyContants.getMenuPermission(userID));
        List<PermissionBean> permissionBean = JSON.parseObject(result,List.class);
        if(parentId!=null){
            this.addRedis(userID,permission,parentId,permissionBean);
        }else{
            PermissionBean permissionBean1 = new PermissionBean();
            permissionBean1.setPermission(permission);
            permissionBean.add(permissionBean1);
            delPermissionRedis(userID);
            //获取MENU列表
            jedisClient.set(RedisKeyContants.getMenuPermission(userID), JSONArray.toJSONString(permissionBean), StaticContants.DEFAULT_SECONDS);
        }*/
    }
    /*private void addRedis(String userID,Permission permission , Integer parentId , List<PermissionBean> permissionBeanList){
        Boolean findParent = false;
        for(int i=0;i<permissionBeanList.size();i++){
            PermissionBean permission1 = permissionBeanList.get(i);
            if(permission1.getPermission().getId() == parentId){
                List<PermissionBean> permissionbeans2 = permission1.getPermissionBeans();
                PermissionBean permissionBeans = new PermissionBean();
                permissionBeans.setPermission(permission);
                permissionbeans2.add(permissionBeans);
                findParent = true;
                //获取MENU列表
                jedisClient.set(RedisKeyContants.getMenuPermission(userID), JSONArray.toJSONString(permissionbeans2), StaticContants.DEFAULT_SECONDS);
                break;
            }
        }
        if(findParent==false){
            for(int i=0;i<permissionBeanList.size();i++){
                PermissionBean permission1 = permissionBeanList.get(i);
                addRedis(userID,permission,parentId,permission1.getPermissionBeans());
            }
        }
    }*/


}
