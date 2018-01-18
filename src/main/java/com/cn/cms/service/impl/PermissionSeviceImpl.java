package com.cn.cms.service.impl;

import com.cn.cms.dao.PermissionDao;
import com.cn.cms.po.Permission;
import com.cn.cms.po.PermissionUser;
import com.cn.cms.po.User;
import com.cn.cms.service.PermissionSevice;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Administrator on 2017/12/20 0020.
 */
@Repository
public class PermissionSeviceImpl implements PermissionSevice {

    @Resource
    PermissionDao permissionDao;

    @Override
    public List<Permission> findPermissionColumn(String user, Integer platform){
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

    @Override
    public Permission queryPermissionColumn(Integer id){
        return permissionDao.queryPermissionColumn(id);
    }

    @Override
    public void deletePermissionColumn(Integer columnId){
        permissionDao.deletePermissionColumn(columnId);
    }

    @Override
    public void updatePermissionColumn(Permission permission){
        permissionDao.updatePermissionColumn(permission);
    }



}
