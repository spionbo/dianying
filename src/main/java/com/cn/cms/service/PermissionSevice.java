package com.cn.cms.service;

import com.cn.cms.po.Permission;
import com.cn.cms.po.PermissionUser;
import com.cn.cms.po.User;
import com.cn.cms.po.UserPower;

import java.lang.reflect.Array;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/12/20 0020.
 */
public interface PermissionSevice {

    //================= 查询 ==============================

    /**
     * 根据positionId列表获取权限  决定是否展示栏目
     * @param userId
     * @paream platform
     * @return
     */
    List<Permission> findPermissionColumn(String userId, Integer platform);

    /**
     * 查询所有栏目
     * @return
     */
    List<Permission> queryPermissionAll();

    /**
     * 查找用户权限 决定用户点击栏目时，是否能请求接口
     */
    List<UserPower> queryUserpower(String userId);

    /* 查询索引*/
    Integer queryPermissionName(Integer parentId,String name);

    /* 查询指定栏目名称 */
    Permission queryPermissionColumn(Integer id);

    //=================== end 查询 ===========================



    // ===================== 创建 ========================

    /**
     * 创建后台栏目权限
     */
    void savePermissionPower(List<Map<String,String>> list);

    /**
     * 创建后台栏目权限
     */
    void savePermissionColumnUser(PermissionUser permissionUser);

    /**
     * 创建用户权限（初始化建表） 表
     * @param userId
     */
    void createUserPowerTable(List<Permission> list , String userId);

    /**
     * 创建后台栏目
     */
    void savePermissionColumn(Permission permission);

    // ==================== end 创建 ===========================

    //==================== 更新 ===========================

    /*更新后台栏目*/
    void updatePermissionColumn(Permission permission);

    // ===================== end 更新 =============================

    //==================== 删除 ===========================

    /**
     * 删除用户权限（清空） 表
     * @param userId
     */
    void deleteUserPowerTable(String userId);

    /*删除后台栏目*/
    void deletePermissionColumn(Integer columnId);


    //===================== end 删除 =============================
}
