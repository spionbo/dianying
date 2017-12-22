package com.cn.cms.service;

import com.cn.cms.po.User;
import com.cn.cms.utils.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;

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
     * 查找列表
     * @param page
     * @return
     */
    List<User> queryUserList(Page page);
}
