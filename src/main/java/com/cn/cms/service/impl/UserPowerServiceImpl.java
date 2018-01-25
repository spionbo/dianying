package com.cn.cms.service.impl;

import com.cn.cms.dao.PermissionUserPowerDao;
import com.cn.cms.po.UserPower;
import com.cn.cms.service.PermissionUserPowerService;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/1/24 0024.
 */
public class PermissionUserPowerServiceImpl implements PermissionUserPowerService{

    @Resource
    private PermissionUserPowerDao userPowerDao;

    @Override
    public Integer getUserPowerCount( String userId){
        return userPowerDao.getUserPowerCount(userId);
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
