package com.cn.cms.service.impl;

import com.cn.cms.dao.PermissionDao;
import com.cn.cms.dao.UserDao;
import com.cn.cms.po.Permission;
import com.cn.cms.po.PermissionUser;
import com.cn.cms.po.User;
import com.cn.cms.po.UserPower;
import com.cn.cms.service.UserService;
import com.cn.cms.utils.Page;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

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
    public User getUserInfo(String userId){
        return userDao.getUserInfo(userId);
    }

    @Override
    public List<User>queryUserList(Page page){
        return userDao.queryUserList(page);
    }

    @Override
    public Integer queryAdmin(String id){
        return userDao.queryAdmin(id);
    }

    @Override
    public Integer queryUserName(String userName) {
        return userDao.queryUserName(userName);
    }

    @Override
    public void createUser( User user){
        userDao.saveUser(user);
    }

    @Override
    public void deleteUser(String userId){ userDao.deleteUser(userId);};

    @Override
    public void updateUser(User user){
        userDao.updateUser(user);
    }

}
