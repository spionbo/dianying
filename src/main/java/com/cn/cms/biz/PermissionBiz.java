package com.cn.cms.biz;

import com.alibaba.fastjson.JSONArray;
import com.cn.cms.bo.PermissionBean;
import com.cn.cms.contants.RedisKeyContants;
import com.cn.cms.contants.StaticContants;
import com.cn.cms.enums.PermissionTypeEnum;
import com.cn.cms.enums.PlatformEnum;
import com.cn.cms.enums.ShowFlagEnum;

import com.cn.cms.middleware.JedisClient;
import com.cn.cms.po.Permission;
import com.cn.cms.service.UserService;
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

    public PermissionBean setColumn(List<PermissionBean> resultList , Map<Integer,List<Permission>> childMap){
        PermissionBean permissionBean = new PermissionBean();
        for(int i=0;i<resultList.size();i++){
            permissionBean = resultList.get(i);
            permissionBean.setPermissions(childMap.get(permissionBean.getPermission().getPermissionId()));
        }
        return permissionBean;
    }

    public void setPermissionRedis(String userId, PlatformEnum platformEnum){
        List<Permission> permissions = userService.findPermissionColumn(userId,platformEnum.getType());
        if(StringUtils.isNotEmpty(permissions)){
            List<PermissionBean> permissionBeenList = new ArrayList<>();
            Map<Integer,List<Permission>> childMap = new HashMap<>();

            for(int i=0;i<permissions.size();i++){
                Permission permission = permissions.get(i);
                if(permission.getParentId()==null){
                    PermissionBean permissionBean = new PermissionBean();
                    permissionBean.setPermission(permission);
                    permissionBeenList.add(permissionBean);
                }else{
                    List<Permission> tmp = new ArrayList<>();
                    tmp.add(permission);
                    childMap.put(permission.getParentId() ,tmp);
                }
            }
            for(int i=0;i<permissionBeenList.size();i++){
                PermissionBean permissionBean = permissionBeenList.get(i);
                permissionBean.setPermissions(childMap.get(permissionBean.getPermission().getId()));
            }

            setColumn(permissionBeenList,childMap);
            if(platformEnum.getType() == PlatformEnum.CMS.getType()) {

            }
        }

        /*List<Permission> permissions = userService.findPermissionForPositionIds(userId, platformEnum.getType());*/
        /*if(StringUtils.isNotEmpty(permissions)) {
            Map<String, Double> map = new HashMap<>();
            //List<Permission> permissionList = new ArrayList<>();
            List<PermissionBean> permissionBeenList = new ArrayList<>();
            Map<Long, List<Permission>> childMap = new HashMap<>();
            for (int i = 0; i < permissions.size(); i++) {
                Permission permission = permissions.get(i);
                map.put(permission.getPermission(), new Double((double)permission.getSort()));
                if(permission.getType() == PermissionTypeEnum.MENU.getType()){
                    //permissionList.add(permission);
                    PermissionBean permissionBean = new PermissionBean();
                    permissionBean.setPermission(permission);
                    permissionBeenList.add(permissionBean);
                }else if(permission.getType() == PermissionTypeEnum.BUTTON.getType()){
                    List<Permission> tmp = childMap.get(permission.getParentId());
                    if(StringUtils.isEmpty(tmp)){
                        tmp = new ArrayList<>();
                    }
                    if(permission.getShowFlag() == ShowFlagEnum.YES.getType()) {
                        tmp.add(permission);
                    }
                    childMap.put(permission.getParentId() ,tmp);
                }
            }
            for(int i=0;i<permissionBeenList.size();i++){
                PermissionBean permissionBean = permissionBeenList.get(i);
                permissionBean.setPermissions(childMap.get(permissionBean.getPermission().getId()));
            }
            if(platformEnum.getType() == PlatformEnum.CMS.getType()) {
                delPermissionRedis(userId);
                //验证用户权限
                jedisClient.zadd(RedisKeyContants.getPermission(userId), map, StaticContants.DEFAULT_SECONDS);
                //获取MENU列表
                jedisClient.set(RedisKeyContants.getMenuPermission(userId), JSONArray.toJSONString(permissionBeenList), StaticContants.DEFAULT_SECONDS);
                //jedisClient.set(redisMap,StaticContants.DEFAULT_SECONDS); //根据父权限获取子权限的列表
            }else if(platformEnum.getType() == PlatformEnum.APP.getType()){
                delAppPermissionRedis(userId);
                //验证用户权限
                jedisClient.zadd(RedisKeyContants.getAppPermission(userId), map, StaticContants.DEFAULT_SECONDS);
                //获取MENU列表
                jedisClient.set(RedisKeyContants.getAppMenuPermission(userId), JSONArray.toJSONString(permissionBeenList), StaticContants.DEFAULT_SECONDS);
                //jedisClient.set(redisMap,StaticContants.DEFAULT_SECONDS); //根据父权限获取子权限的列表
            }
        }*/
    }
}
