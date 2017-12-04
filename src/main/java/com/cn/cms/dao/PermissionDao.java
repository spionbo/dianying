package com.cn.cms.dao;

import com.cn.cms.po.Permission;
import com.cn.cms.utils.Page;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * Created by Administrator on 2017/11/17 0017.
 */
public interface PermissionDao {

    Integer queryColumnCount(@Param(value = "userId") String userId, @Param(value = "publish") Integer publish, @Param(value = "delTag") Integer delTag);

    List<Permission> findPermissionColumn(@Param(value = "userId") String userId ,@Param(value="platform") Integer platform );

    List<Permission> queryColumnList(@Param(value="userId") String userId,
                                     @Param(value="publish") Integer publish,
                                     @Param(value = "delTag") Integer delTag,
                                     @Param(value = "page")Page page);
}
