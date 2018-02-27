package com.cn.cms.service.impl;

import com.cn.cms.dao.UserPowerDao;
import com.cn.cms.po.Permission;
import com.cn.cms.po.UserPower;
import com.cn.cms.service.UserPowerService;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/1/24 0024.
 */
@Repository
public class UserPowerServiceImpl implements UserPowerService {

    @Resource
    private UserPowerDao userPowerDao;

    @Override
    public Integer getUserPowerCount( String userId){
        return userPowerDao.getUserPowerCount(userId);
    };

    @Override
    public void createUserPower(List<Permission> list, String userId){
        userPowerDao.createUserPower(list,userId);
    };

    @Override
    public void deleteUserPower(String userId){
        userPowerDao.deleteUserPower(userId);
    };

    @Override
    public void deleteUserPower(Boolean bool,Integer permissionId){
        userPowerDao.deleteUserPower(bool,permissionId);
    };

    @Override
    public List<UserPower> getUserPower(String userId){
        return userPowerDao.getUserPower(userId);
    };

    @Override
    public void updateUserPower(List<Map<String,String>> list){
        userPowerDao.updateUserPower(list);
    }
}
