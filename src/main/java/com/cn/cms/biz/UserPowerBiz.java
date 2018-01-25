package com.cn.cms.biz;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.cn.cms.contants.RedisKeyContants;
import com.cn.cms.contants.StaticContants;
import com.cn.cms.enums.PlatformEnum;
import com.cn.cms.middleware.JedisClient;
import com.cn.cms.po.Permission;
import com.cn.cms.po.UserPower;
import com.cn.cms.service.PermissionUserPowerService;
import com.cn.cms.utils.StringUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.*;

/**
 * 用户栏目权限管理
 * Created by Administrator on 2018/1/24 0024.
 */
@Component
public class PermissionUserPowerBiz {

    @Resource
    private PermissionUserPowerService userPowerService;

    @Resource
    private PermissionBiz permissionBiz;

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
            jedisClient.set(RedisKeyContants.getUserColumnPower(userId),JSONArray.toJSONString(userPowers), StaticContants.DEFAULT_SECONDS);
        }
        return userPowers;
    }

    /**
     * 更新后台栏目权限 读 更 写 删
     * @param power
     */
    public void updateUserPower(String power , String userId){

        Integer count = permissionBiz.queryPermissionColumnCount(1);
        Integer powerCount = getUserPowerCount(userId);
        if(count > powerCount){
            //管理员拥有多少权限，则只能指定其他人最多拥有管理员相同的权限
            List<Permission> permissions = permissionBiz.getAllColumn(userId, PlatformEnum.CMS);
            List<UserPower> userPowerList = getUserPower(userId);
            List<UserPower> newList = new ArrayList<>();

            for (int i=0;i<permissions.size();i++){
                Permission permission = permissions.get(i);

            }
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
    }
}
