package com.cn.cms.biz;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.cn.cms.bo.UserBean;
import com.cn.cms.contants.RedisKeyContants;
import com.cn.cms.contants.StaticContants;
import com.cn.cms.enums.PlatformEnum;
import com.cn.cms.middleware.JedisClient;
import com.cn.cms.po.Permission;
import com.cn.cms.po.UserPower;
import com.cn.cms.service.PermissionSevice;
import com.cn.cms.service.UserPowerService;
import com.cn.cms.utils.StringUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.*;

/**
 * 用户栏目权限管理
 * Created by Administrator on 2018/1/24 0024.
 */
@Component
public class UserPowerBiz {

    @Resource
    private UserPowerService userPowerService;

    @Resource
    private PermissionBiz permissionBiz;

    @Resource
    private PermissionSevice permissionSevice;

    @Resource
    private UserBiz userBiz;

    @Resource
    private JedisClient jedisClient;

    /**
     * 查看用户拥有栏目权限的数量
     * @param userId
     * @return
     */
    public Integer getUserPowerCount(String userId){
        return userPowerService.getUserPowerCount(userId);
    }

    /**
     * 创建用户栏目控制权限
     * @param userId
     */
    public void createUserPower(String userId){
        List<Permission> lists = permissionSevice.queryPermissionAll();
        createUserPower(userId,lists);
    }
    private void createUserPower(String userId , List<Permission> lists ){
        UserBean userBean = userBiz.getUserBean(userId);
        for (int i=0;i<lists.size();i++){
            Permission permission = lists.get(i);
            permission.setLastModifyUserId(userBean.getUserId());
            permission.setCreateUserId(userBean.getUserId());
            permission.setLastModifyUserName(userBean.getRealName());
            permission.setUpdateTimeStr(userBean.getUpdateTimeStr());
            permission.setCreateTimeStr(userBean.getCreateTimeStr());
            permission.setCreateUserName(userBean.getRealName());
        }
        userPowerService.createUserPower(lists,userId);
    }

    /**
     * 删除用户栏目控制权限
     * @param userId
     */
    public void deleteUserPower(String userId){
        userPowerService.deleteUserPower(userId);
    }
    public void deleteUserPower(Boolean bool,Integer permissionId){
        userPowerService.deleteUserPower(bool,permissionId);
    }

    /**
     * 查看用户拥有的栏目权限 write read update delete
     * @param userId
     * @return
     */
    public List<UserPower> getUserPower(String userId){
        List<UserPower> userPowers;
        String result = jedisClient.get(RedisKeyContants.getUserColumnPower(userId));
        if(result!=null && StringUtils.isNotEmpty(result)){
            List<UserPower> list = JSONArray.parseArray(result, UserPower.class);
            userPowers = JSONArray.parseArray(result,UserPower.class);
        }else{
            userPowers = userPowerService.getUserPower(userId);
            setRedis(userId,userPowers);
        }
        return userPowers;
    }

    /**
     * 更新后台栏目权限 读 更 写 删
     * @param power
     */
    public void updateUserPower(String power , String userId){
        //获取栏目数量
        Integer count = permissionBiz.queryPermissionColumnCount(1);
        //获取用户对栏目权限控制的库。不存在则创建
        Integer powerCount = getUserPowerCount(userId);
        if(powerCount > 0 && count > powerCount){  //权限表里面的字段和实际permission栏目表的字段对不上，则说明创建用户后，又创建栏目了。
            //管理员拥有多少权限，则只能指定其他人最多拥有管理员相同的权限
            List<Permission> permissions = permissionBiz.getAllColumn(userId, PlatformEnum.CMS);
            List<UserPower> userPowerList = getUserPower(userId);
            List<Permission> newList = new ArrayList<>();

            for (int i=0;i<permissions.size();i++){
                Permission permission = permissions.get(i);
                Boolean hasPermission = false;
                for(int k=0;k<userPowerList.size();k++){
                    UserPower userPower = userPowerList.get(k);
                    if(userPower.getPermissionId().equals(permission.getId())){
                        hasPermission = true;
                        break;
                    }
                }
                if(!hasPermission){
                    newList.add(permission);
                }
            }
            createUserPower( userId , newList );
        }
        if(powerCount<1){ //用户提前创建的情况
            createUserPower( userId );
        }

        JSONArray jsonArray = JSON.parseArray(power);
        Iterator<?> it = jsonArray.iterator();
        List<Map<String,String>> list = new ArrayList<>();

        while (it.hasNext()){
            JSONObject obj = (JSONObject) it.next();
            Map<String,String> map = new HashMap<>();

            try{
                map.put("userId",obj.get("userId").toString());
                map.put("permissionId",obj.get("permissionId").toString());
                map.put("power",obj.get("power").toString());
            }catch (Exception e){
                e.printStackTrace();
            }
            list.add(map);
        }
        userPowerService.updateUserPower(list);
        updateRedis(userId);
    }

    private void setRedis(String userId,List<UserPower> userPowers){
        jedisClient.set(RedisKeyContants.getUserColumnPower(userId),JSONArray.toJSONString(userPowers), StaticContants.DEFAULT_SECONDS);
    }
    private void updateRedis(String userId){
        List<UserPower> userPowers = userPowerService.getUserPower(userId);
        setRedis(userId,userPowers);
    }
}
