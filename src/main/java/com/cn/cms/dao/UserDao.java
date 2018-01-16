package com.cn.cms.dao;

import com.cn.cms.po.User;
import com.cn.cms.utils.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by Administrator on 2017/11/6 0006.
 */
public interface UserDao {

    User findUserName(@Param(value = "userName") String userName);

    List<User> queryUserList(@Param(value = "page") Page page);

    Integer queryUserCount();

    Integer queryAdmin(@Param(value = "userId") String userId);
}
