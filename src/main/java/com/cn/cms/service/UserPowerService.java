package com.cn.cms.service;

import com.cn.cms.po.Permission;
import com.cn.cms.po.UserPower;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/1/24 0024.
 */
public interface UserPowerService {

    /**
     *获取栏目权限总数
     */
    Integer getUserPowerCount( String userId);


    /**
     * 查看用户栏目权限 更 读 写 删
     */
    List<UserPower> getUserPower(String userId);

    /**
     * 创建用户栏目权限控制
     */
    void createUserPower(List<Permission> list, String userId);

    /**
     * 删除用户权限（清空） 表
     * @param userId
     */
    void deleteUserPower(String userId);
    void deleteUserPower(Boolean bool,Integer permissionId);

    /**
     * 更新用户栏目权限 更 读 写 删
     * @param list
     */
    void updateUserPower(List<Map<String,String>> list);
}
