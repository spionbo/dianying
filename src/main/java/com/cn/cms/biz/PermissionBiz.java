package com.cn.cms.biz;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.parser.Feature;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.cn.cms.bo.ColumnBean;
import com.cn.cms.bo.PermissionBean;
import com.cn.cms.bo.UserBean;
import com.cn.cms.contants.PermissionNames;
import com.cn.cms.contants.RedisKeyContants;
import com.cn.cms.contants.StaticContants;
import com.cn.cms.enums.ErrorCodeEnum;
import com.cn.cms.enums.PermissionTypeEnum;
import com.cn.cms.enums.PlatformEnum;
import com.cn.cms.enums.ShowFlagEnum;

import com.cn.cms.middleware.JedisClient;
import com.cn.cms.po.Permission;
import com.cn.cms.po.PermissionUser;
import com.cn.cms.po.UserPower;
import com.cn.cms.service.ColumnService;
import com.cn.cms.service.PermissionSevice;
import com.cn.cms.service.UserService;
import com.cn.cms.utils.Page;
import com.cn.cms.utils.StringUtils;
import com.cn.cms.web.ann.CheckAuth;
import com.cn.cms.web.ann.CheckToken;
import com.cn.cms.web.result.ApiResponse;
import freemarker.template.utility.StringUtil;
import lombok.Getter;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.lang.reflect.Array;
import java.util.*;

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
    private UserPowerBiz userPowerBiz;

    @Resource
    private JedisClient jedisClient;


    /**
     * 获取所有栏目
     * @return
     */
    public List<Permission> getAllColumn(String userId,PlatformEnum platformEnum){
        List<Permission> list = PermissionNames.getAllcolumn();
        if(list==null){
            updatePermissionColumn(userId, platformEnum);
            return PermissionNames.getAllcolumn();
        }
        return list;
    }

    /**
     * 获取所有栏目 为 树状
     * @return
     */
    public List<PermissionBean> getAllColumnBean(String userId,PlatformEnum platformEnum){
        List<PermissionBean> list = PermissionNames.getAllcolumnPermissionBeans();
        if(list==null){
            updatePermissionColumn(userId, platformEnum);
            return PermissionNames.getAllcolumnPermissionBeans();
        }
        return list;
    }

    /**
     * 获取栏目数量
     */
    public Integer queryPermissionColumnCount(Integer delTag){
        return permissionSevice.queryPermissionColumnCount(delTag);
    }


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
     * 获取超级管理员权限
     * @param userId
     * @return
     */
    private Boolean getAdmin(String userId){
        Integer count = userService.queryAdmin(userId);
        if(count!=null && count > 0){
            return true;
        }
        return false;
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
        if(getAdmin(userId)){
            return true;
        }
        List<UserPower> userPowers = userPowerBiz.getUserPower(userId);
        String arr[] = permission.split(":");
        String key = arr[0];
        String val = arr[1];
        if(PermissionNames.map==null){
            updatePermissionColumn(userId,PlatformEnum.CMS);
        }
        Integer id = PermissionNames.map.get(key);
        for (int i = 0;i<userPowers.size();i++){
            UserPower userPower = userPowers.get(i);
            if(userPower.getPermissionId() == id){
                String power = userPower.getPower();
                if(power!=null&&power.indexOf(val)>-1){
                    return true;
                }
            }
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
     * 更新栏目信息 添加到 redis 和静态类中
     * @param userId
     * @param platformEnum
     * @return
     */
    public String updatePermissionColumn(String userId, PlatformEnum platformEnum){
        Boolean isAdmin = getAdmin(userId);
        List<PermissionBean> permissionBeans = PermissionNames.getAllcolumnPermissionBeans();

        /**
         * 设置静态属性
         */
        if(StringUtils.isEmpty(permissionBeans)){
            List<Permission> list = permissionSevice.queryPermissionAll();
            permissionBeans = setColumnToTree(list);
            PermissionNames.init( list , permissionBeans );
        }
        /**
         * end 设置静态属性
         */

        if(!isAdmin){ //非超级管理员
            permissionBeans = setColumnToTree(getUserPowerList(userId,PermissionNames.getAllcolumn()));
        }
        //添加栏目权限列表  缓存
        addPermissionCatch(permissionBeans , userId , platformEnum);
        return JSONArray.toJSONString(permissionBeans);
    }

    /**
     * 获取用户栏目权限
     * @param list
     * @return
     */
    private List<Permission> getUserPowerList(String userId,List<Permission> list){
        List<UserPower> userPowers = userPowerBiz.getUserPower(userId);
        List<Permission> newList = new ArrayList<>();

        for(int j=0;j<userPowers.size();j++){
            UserPower userPower = userPowers.get(j);
            String power = userPower.getPower();
            Integer permissionId = userPower.getPermissionId();
            if( power!=null && power.indexOf("read")>-1){
                for(int i=0;i<list.size();i++) {
                    Permission permission = list.get(i);
                    Integer id = permission.getId();
                    if(id.equals(permissionId)){
                        newList.add(permission);
                        break;
                    }
                }
            }
        }
        return newList;
    }

    /**
     * 获取权限列表，
     * @return
     */
    public List<PermissionBean> findPermissionListAndSort(){
        //List<Permission> list = permissionSevice.findPermissionList();
        return null;
    }

    /**
     * 更新栏目 改为 树状
     * @param permissions
     * @return
     */
    private List<PermissionBean> setColumnToTree( List<Permission> permissions ){
        if(StringUtils.isNotEmpty(permissions)){
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
            return T.setColumn(columnBeans, catchList);
        }
        return null;
    }

    /**
     * 添加栏目缓存
     * @param list
     * @param userId
     * @param platformEnum
     */
    private void addPermissionCatch(List<PermissionBean> list, String userId, PlatformEnum platformEnum){
        String Lists = JSONArray.toJSONString(list);
        if(StringUtils.isNotEmpty(list)){
            if(platformEnum.getType() == PlatformEnum.CMS.getType()) { //cms 为PC
                delPermissionRedis(userId);
                //获取MENU列表
                jedisClient.set(RedisKeyContants.getMenuPermission(userId), Lists, StaticContants.DEFAULT_SECONDS);

            }else if(platformEnum.getType() == PlatformEnum.APP.getType()){
                delAppPermissionRedis(userId);
                //获取MENU列表
                jedisClient.set(RedisKeyContants.getAppMenuPermission(userId), Lists, StaticContants.DEFAULT_SECONDS);
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
    public void savePermissionColumn(String userID,String name,Integer parentId, String url,Integer sort,String description,PlatformEnum platformEnum) {
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

        updatePermissionColumn(userID,platformEnum);
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
        updatePermissionColumn(userID,PlatformEnum.CMS);
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
            return T.getPermissiionId(permissionBean,id);
        }
        return false;
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
            PermissionBean parent = T.getTagColumn(permissionBean,parentId);
            Permission obj = parent.getPermission();
            List<PermissionBean> list = parent.getPermissionBeans();
            if(obj.getId() == id || (obj.getParentId()!=null && obj.getParentId() == id)){
                return true;
            }
            if(list!=null){
                return T.getPermissiionId(list,id);
            }
        }
        return false;
    }

    private static class T{

        /**
         * 解析列表，分类相应栏目
         * @param parentBeans  父list
         * @param allList   所有 list
         * @return
         */
        private static List<PermissionBean> setColumn(List<PermissionBean> parentBeans,List<Permission> allList){

            //筛选出来的list
            List<Permission> screenList;
            for(int i=0;i<parentBeans.size();i++){
                PermissionBean parent = parentBeans.get(i);
                Permission parentPermission = parent.getPermission();

                //获取子类
                List<Permission> firstChild = new ArrayList<>();

                List<Permission> newList = new ArrayList<>();

                //添加 子类 到父类
                for(int j=0;j<allList.size();j++){
                    Permission child = allList.get(j);
                    if(child.getParentId() == parentPermission.getId()){
                        firstChild.add(child);
                    }else{
                        newList.add(child);
                    }
                }
                allList = newList;
                screenList = newList;
                if(firstChild.size()>0){
                    List<PermissionBean> list = new ArrayList<>();
                    //Permission 转 PermissionBean
                    for(int k=0;k<firstChild.size();k++){
                        PermissionBean permissionBean = new PermissionBean();
                        permissionBean.setPermission(firstChild.get(k));
                        list.add(permissionBean);
                    }
                    //(一级)子类获取(二级)子类
                    setColumn(list,screenList);
                    parent.setPermissionBeans(list);
                }
            }
            return parentBeans;
        }

        /**
         * 父ID 是否= id
         * @param list
         * @param id
         * @return
         */
        private static Boolean getPermissiionId(List<PermissionBean> list , Integer id){
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
         * 获取指定ID的栏目
         * @param list
         * @param parentId
         * @return
         */
        private static PermissionBean getTagColumn(List<PermissionBean> list , Integer parentId){
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
}
