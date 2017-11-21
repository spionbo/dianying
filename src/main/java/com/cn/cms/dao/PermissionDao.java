package com.cn.cms.dao;

import com.cn.cms.po.Permission;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created by Administrator on 2017/11/17 0017.
 */
public interface PermissionDao {

    String sql_column = "id,last_modify_user_id,name,description,type,url,sort,parent_id,show_flag,permission,platform,create_user_id";
    String delTag = "where del_tag = 1";

    @Select("select "+sql_column+" from permission "+delTag+" order by sort,update_time asc")
    List<Permission> findPermissionForPositionIds(@Param(value = "userId") String userId, @Param(value = "platform") Integer platform);
}
