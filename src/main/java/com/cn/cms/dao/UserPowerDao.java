package com.cn.cms.dao;

import com.cn.cms.po.Permission;
import com.cn.cms.po.UserPower;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/1/24 0024.
 */
public interface UserPowerDao {

    /**
     * 获取该用户权限下的所有栏目数量
     * @param userId
     * @return
     */
    Integer getUserPowerCount(@Param(value = "userId") String userId);

    /**
     * 获取 用户对permission栏目的权限
     * @param userId
     * @return
     */
    List<UserPower> getUserPower(@Param(value = "userId") String userId);

    /**
     * 创建用户栏目控制权限
     */
    void createUserPower(@Param(value = "list")List<Permission> list , @Param(value = "userId") String userId);

    /**
     * 删除用户栏目控制权限
     * @param userId
     */
    void deleteUserPower(@Param(value = "userId") String userId);
    void deleteUserPower(@Param(value = "bool") Boolean bool,@Param(value = "permissionId") Integer permissionId);

    /**
     * 更新 用户对permission管理的权限
     * @param list
     */
    void updateUserPower(@Param(value = "list")List<Map<String,String>> list);
}
