package com.cn.cms.dao;

import com.cn.cms.po.Permission;
import com.cn.cms.po.PermissionUser;
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

    void savePermissionColumn(@Param(value = "p1") Permission permission);

    void savePermissionColumnUser(@Param(value = "p1") PermissionUser permissionUser );

    Integer queryPermissionName(@Param(value = "parentId") Integer parentId ,
                                @Param(value = "permissionName") String name);

    Permission queryPermissionColumn(@Param(value = "id") Integer id);

    void deletePermissionColumnUser(@Param(value = "userId") String userId);

    void deletePermissionColumn(@Param(value="columnId") Integer columnId);

    void updatePermissionColumn(@Param(value = "p1") Permission permission);
}
