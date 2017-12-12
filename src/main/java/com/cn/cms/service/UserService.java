package com.cn.cms.service;

import com.cn.cms.po.Permission;
import com.cn.cms.po.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by Administrator on 2017/11/3 0003.
 */
public interface UserService {

    /**
     *根据用户名查询用户
     * @param userName
     * @return
     */
    User findUserName(String userName);

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

    Integer queryPermissionName(Integer parentId,String name);
}
