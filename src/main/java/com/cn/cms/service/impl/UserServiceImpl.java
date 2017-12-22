package com.cn.cms.service.impl;

import com.cn.cms.dao.PermissionDao;
import com.cn.cms.dao.UserDao;
import com.cn.cms.po.Permission;
import com.cn.cms.po.PermissionUser;
import com.cn.cms.po.User;
import com.cn.cms.service.UserService;
import com.cn.cms.utils.Page;
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

    @Override
    public Integer queryUserCount() {
        return userDao.queryUserCount();
    }

    @Override
    public User findUserName(String userName){
        return userDao.findUserName(userName);
    }

    @Override
    public List<User>queryUserList(Page page){
        return userDao.queryUserList(page);
    }
}
