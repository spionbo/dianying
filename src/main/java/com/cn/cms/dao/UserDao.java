package com.cn.cms.dao;

import com.cn.cms.po.User;
import org.apache.ibatis.annotations.Param;

/**
 * Created by Administrator on 2017/11/6 0006.
 */
public interface UserDao {

    User findUserName(@Param(value = "userName") String userName);

}
