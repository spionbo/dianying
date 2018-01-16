package com.cn.cms.service;

import com.cn.cms.po.Permission;
import com.cn.cms.po.PermissionUser;
import com.cn.cms.po.User;

import java.util.List;

/**
 * Created by Administrator on 2017/12/20 0020.
 */
public interface PermissionSevice {
    /**
     * 根据positionId列表获取权限
     * @param userId
     * @paream platform
     * @return
     */
    List<Permission> findPermissionColumn(String userId, Integer platform);

    /**
     * 创建后台栏目
     */
    void savePermissionColumn(Permission permission);

    /**
     * 创建后台栏目权限
     */
    void savePermissionColumnUser(PermissionUser permissionUser);
    /* 查询索引*/
    Integer queryPermissionName(Integer parentId,String name);

    /* 查询指定栏目名称 */
    Permission queryPermissionColumn(Integer id);

    /*删除后台栏目*/
    void deletePermissionColumn(Integer columnId);

    /*更新后台栏目*/
    void updatePermissionColumn(Permission permission);

}
