package com.cn.cms.dao;

import com.cn.cms.po.Permission;
import com.cn.cms.po.PermissionUser;
import com.cn.cms.po.User;
import com.cn.cms.po.UserPower;
import com.cn.cms.utils.Page;
import org.apache.ibatis.annotations.Param;

import java.lang.reflect.Array;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/11/17 0017.
 */
public interface PermissionDao {

    Integer queryPermissionColumnCount(@Param(value = "delTag") Integer delTag);

    List<Permission> findPermissionColumn(@Param(value = "userId") String userId ,@Param(value="platform") Integer platform );

    List<Permission> queryPermissionAll();

    void savePermissionColumn(@Param(value = "p1") Permission permission);


    Integer queryPermissionName(@Param(value = "parentId") Integer parentId ,
                                @Param(value = "permissionName") String name);

    Permission queryPermissionColumn(@Param(value = "id") Integer id);

}
