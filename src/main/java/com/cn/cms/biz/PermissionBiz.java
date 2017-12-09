package com.cn.cms.biz;

import com.alibaba.fastjson.JSONArray;
import com.cn.cms.bo.ColumnBean;
import com.cn.cms.bo.PermissionBean;
import com.cn.cms.contants.RedisKeyContants;
import com.cn.cms.contants.StaticContants;
import com.cn.cms.enums.PermissionTypeEnum;
import com.cn.cms.enums.PlatformEnum;
import com.cn.cms.enums.ShowFlagEnum;

import com.cn.cms.middleware.JedisClient;
import com.cn.cms.po.Permission;
import com.cn.cms.service.ColumnService;
import com.cn.cms.service.UserService;
import com.cn.cms.utils.Page;
import com.cn.cms.utils.StringUtils;
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

    private List<PermissionBean> setColumn(List<PermissionBean> permissionBeans,List<Permission> catchList){

        for(int i=0;i<permissionBeans.size();i++){
            PermissionBean parent = permissionBeans.get(i);
            Permission parentPermission = parent.getPermission();

            List<Permission> childList = new ArrayList<>();
            List<PermissionBean> list = new ArrayList<>();

            for(int j=0;j<catchList.size();j++){
                Permission child = catchList.get(j);
                if(child.getParentId() == parentPermission.getId()){
                    childList.add(child);
                }
            }
            for(int k=0;k<childList.size();k++){
                PermissionBean permissionBean = new PermissionBean();
                permissionBean.setPermission(childList.get(k));
                list.add(permissionBean);
            }
            parent.setPermissionBeans(list);
        }
        return permissionBeans;
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

    public void savePermissionColumn(Permission permission){
        userService.savePermissionColumn(permission);
    }


}
