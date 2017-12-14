package com.cn.cms.service.impl;

import com.cn.cms.dao.PermissionDao;
import com.cn.cms.dao.UserDao;
import com.cn.cms.po.Permission;
import com.cn.cms.po.PermissionUser;
import com.cn.cms.po.User;
import com.cn.cms.service.UserService;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Administrator on 2017/11/3 0003.
 */
@Repository
public class UserServiceImpl implements UserService {

    @Resource
    UserDao userDao;

    @Resource
    PermissionDao permissionDao;

    @Override
    public User findUserName(String userName){
        return userDao.findUserName(userName);
    }

    @Override
    public List<Permission> findPermissionColumn(String user,Integer platform){
        return permissionDao.findPermissionColumn(user,platform);
    }

    @Override
    public void savePermissionColumn(Permission permission){
        permissionDao.savePermissionColumn(permission);
    }

    @Override
    public void savePermissionColumnUser(PermissionUser permissionUser){
        permissionDao.savePermissionColumnUser(permissionUser);
    }

    @Override
    public Integer queryPermissionName(Integer parentId,String name){
        return permissionDao.queryPermissionName(parentId,name);
    };
}
