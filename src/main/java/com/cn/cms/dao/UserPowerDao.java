package com.cn.cms.dao;

import com.cn.cms.po.UserPower;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/1/24 0024.
 */
public interface PermissionUserPowerDao {

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
     * 更新 用户对permission管理的权限
     * @param list
     */
    void updateUserPower(@Param(value = "list")List<Map<String,String>> list);
}
