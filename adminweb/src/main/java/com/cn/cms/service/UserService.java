package com.cn.cms.service;

import com.cn.cms.po.User;
import com.cn.cms.po.UserPower;
import com.cn.cms.utils.Page;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/11/3 0003.
 */
public interface UserService {

    /**
     * 查询用户的总数
     * @return
     */
    Integer queryUserCount();

    /**
     *根据用户名查询用户
     * @param userName
     * @return
     */
    User findUserName(String userName);

    /**
     *根据ID查询用户
     * @param userId
     * @return
     */
    User getUserInfo(String userId);

    /**
     * 获取是否存在用户名
     * @param userName
     * @return
     */
    Integer queryUserName(String userName);

    /**
     * 查找列表
     * @param page
     * @return
     */
    List<User> queryUserList(Page page);

    /*获取超级管理员*/
    Integer queryAdmin(String id);

    /*
    * 新增管理员
    * @param user
    * */
    void createUser(User user);

    /**
     * 删除管理员
     * @param userId
     */
    void deleteUser(String userId);

    /**
     * 更新管理员 或 更改个人信息
     * @param user
     */
    void updateUser(User user);

}
