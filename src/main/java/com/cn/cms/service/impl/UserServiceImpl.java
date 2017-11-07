package com.cn.cms.service.impl;

import com.cn.cms.dao.UserDao;
import com.cn.cms.po.User;
import com.cn.cms.service.UserService;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

/**
 * Created by Administrator on 2017/11/3 0003.
 */
@Repository
public class UserServiceImpl implements UserService {

    @Resource
    UserDao userDao;

    public User findUserName(String userName){
        return userDao.findUserName(userName);
    }
}
