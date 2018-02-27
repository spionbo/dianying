package com.cn.cms.dao;

import com.cn.cms.po.User;
import com.cn.cms.po.UserPower;
import com.cn.cms.utils.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/11/6 0006.
 */
public interface UserDao {

    User findUserName(@Param(value = "userName") String userName);

    User getUserInfo(@Param(value = "userId") String userId);

    List<User> queryUserList(@Param(value = "page") Page page);

    Integer queryUserCount();

    Integer queryAdmin(@Param(value = "userId") String userId);

    void saveUser(@Param(value = "user") User user);

    Integer queryUserName(@Param(value = "userName") String userName);

    void deleteUser(@Param(value="userId") String userId);

    void updateUser(@Param(value = "user") User user);


}
