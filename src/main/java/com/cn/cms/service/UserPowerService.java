package com.cn.cms.service;

import com.cn.cms.po.UserPower;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/1/24 0024.
 */
public interface PermissionUserPowerService {

    /**
     *获取栏目权限总数
     */
    Integer getUserPowerCount( String userId);


    /**
     * 查看用户栏目权限 更 读 写 删
     */
    List<UserPower> getUserPower(String userId);

    /**
     * 更新用户栏目权限 更 读 写 删
     * @param list
     */
    void updateUserPower(List<Map<String,String>> list);
}
