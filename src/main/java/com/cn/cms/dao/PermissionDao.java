package com.cn.cms.dao;

import com.cn.cms.po.Permission;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * Created by Administrator on 2017/11/17 0017.
 */
public interface PermissionDao {

    List<Permission> findPermissionColumn(@Param(value = "userId") String userId ,@Param(value="platform") Integer platform );
}
