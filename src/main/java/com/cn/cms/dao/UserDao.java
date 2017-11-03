package com.cn.cms.dao;

import com.cn.cms.po.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * Created by Administrator on 2017/11/3 0003.
 */
public interface UserDao {

    @Select("select id,name from second_column where column_id=#{id}")
    User findUserName(@Param(value = "userName") String userName);
}
