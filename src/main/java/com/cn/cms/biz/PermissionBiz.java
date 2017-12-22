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
import com.cn.cms.service.PermissionSevice;
import com.cn.cms.service.UserService;
import com.cn.cms.utils.Page;
import com.cn.cms.utils.StringUtils;
import com.cn.cms.web.ann.CheckAuth;
import com.cn.cms.web.ann.CheckToken;
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
    private PermissionSevice permissionSevice;

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
        return permissionSevice.queryPermissionName(parentId,name);
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
        return permissionSevice.queryPermissionColumn(id);
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
        List<Permission> permissions = permissionSevice.findPermissionColumn(userId,platformEnum.getType());
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

    /**
     * 新增栏目
     * @param userID
     * @param name
     * @param parentId
     * @param url
     * @param sort
     * @param description
     */
    public void savePermissionColumn(String userID,String name,Integer parentId, String url,Integer sort,String description) {
        Permission permission = new Permission();
        PermissionUser permissionUser = new PermissionUser();

        permission.setName(name);
        permission.setLastModifyUserId(userID);
        permission.setCreateUserId(userID);
        if (StringUtils.isNotBlank(url)) {
            permission.setUrl(url);
        }
        if (StringUtils.isNotBlank(description)) {
            permission.setDescription(description);
        }
        if (parentId != null) {
            permission.setParentId(parentId);
        }
        if (sort != null) {
            permission.setSort(sort);
        }
        permissionSevice.savePermissionColumn(permission);
        permissionUser.setUserId(userID);
        permissionUser.setPositionId(permission.getId());
        permissionSevice.savePermissionColumnUser(permissionUser);
    }

    /**
     * 删除栏目
     * @param columnId
     * @param userId
     * @return
     */
    public void deletePermissionColumn(Integer columnId , String userId){
        permissionSevice.deletePermissionColumn(columnId);
    }

    /**
     * 更新栏目信息
     * @param userID
     * @param name
     * @param parentId
     * @param url
     * @param sort
     * @param description
     */
    public void updatePermissionColumn(Integer id ,String userID,String name,Integer parentId , String url,Integer sort,String description){
        Permission permission = new Permission();
        PermissionUser permissionUser = new PermissionUser();

        permission.setId(id);
        permission.setName(name);
        permission.setLastModifyUserId(userID);
        permission.setCreateUserId(userID);
        if (StringUtils.isNotBlank(url)) {
            permission.setUrl(url);
        }
        if (StringUtils.isNotBlank(description)) {
            permission.setDescription(description);
        }
        if (parentId != null) {
            permission.setParentId(parentId);
        }
        if (sort != null) {
            permission.setSort(sort);
        }
        permissionSevice.updatePermissionColumn(permission);
    }


    /**
     * 判断该栏目是否有子栏目
     * @param id
     * @param userId
     * @return
     */
    public Boolean hasPermission(Integer id , String userId){
        String result = jedisClient.get(RedisKeyContants.getMenuPermission(userId));

        if(StringUtils.isNotBlank(result)) {
            List<PermissionBean> permissionBean = JSONArray.parseArray(result, PermissionBean.class);
            return getPermissiionId(permissionBean,id);
        }
        return false;
    }

    /**
     * 父ID 是否= id
     * @param list
     * @param id
     * @return
     */
    private Boolean getPermissiionId(List<PermissionBean> list , Integer id){
        Boolean bool = false;
        for(int i=0;i<list.size();i++){
            PermissionBean permissionBean1 = list.get(i);
            Permission permission = permissionBean1.getPermission();
            List<PermissionBean> permissionBeans = permissionBean1.getPermissionBeans();
            if(permission.getParentId()!=null && permission.getParentId() == id){
                bool = true;
                break;
            };
            if(bool==false && permissionBeans!=null){
                bool = getPermissiionId(permissionBeans,id);
            }
        }
        return bool;
    }

    /**
     * 是否是将父栏目添加其他栏目  如果是父栏目的子栏目则返回true 不可操作
     * @param id
     * @param userId
     * @return
     */
    public Boolean hasAddToChild(Integer id , Integer parentId , String userId){
        String result = jedisClient.get(RedisKeyContants.getMenuPermission(userId));

        if(StringUtils.isNotBlank(result)) {
            List<PermissionBean> permissionBean = JSONArray.parseArray(result, PermissionBean.class);
            PermissionBean parent = getTagColumn(permissionBean,parentId);
            Permission obj = parent.getPermission();
            List<PermissionBean> list = parent.getPermissionBeans();
            if(obj.getId() == id || (obj.getParentId()!=null && obj.getParentId() == id)){
                return true;
            }
            if(list!=null){
                return getPermissiionId(list,id);
            }
        }
        return false;
    }

    /**
     * 获取指定ID的栏目
     * @param list
     * @param parentId
     * @return
     */
    private PermissionBean getTagColumn(List<PermissionBean> list , Integer parentId){
        PermissionBean parentBean = new PermissionBean();
        for(int i=0;i<list.size();i++){
            PermissionBean permissionBean1 = list.get(i);
            Permission permission = permissionBean1.getPermission();
            List<PermissionBean> permissionBeans = permissionBean1.getPermissionBeans();
            if(permission.getId() == parentId){
                parentBean.setPermission(permission);
                parentBean.setPermissionBeans(permissionBeans);
                break;
            };
            if(parentBean.getPermission()==null && permissionBeans!=null){
                parentBean = getTagColumn(permissionBeans,parentId);
            }
        }
        return parentBean;
    }
}
