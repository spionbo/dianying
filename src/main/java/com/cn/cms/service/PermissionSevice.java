package com.cn.cms.service;

import com.cn.cms.po.Permission;
import com.cn.cms.po.PermissionUser;
import com.cn.cms.po.User;
import com.cn.cms.po.UserPower;

import java.lang.reflect.Array;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/12/20 0020.
 */
public interface PermissionSevice {

    //================= 查询 ==============================

    /**
     * 查询所有栏目
     * @return
     */
    List<Permission> queryPermissionAll();

    /**
     * 查询所有栏目 数量
     */
    Integer queryPermissionColumnCount(Integer delTag);

    /* 查询索引*/
    Integer queryPermissionName(Integer parentId,String name);

    /* 查询指定栏目名称 */
    Permission queryPermissionColumn(Integer id);

    //=================== end 查询 ===========================



    // ===================== 创建 ========================

    /**
     * 创建后台栏目
     */
    void savePermissionColumn(Permission permission);

    // ==================== end 创建 ===========================

    //==================== 更新 ===========================

    /*更新后台栏目*/
    void updatePermissionColumn(Permission permission);

    // ===================== end 更新 =============================

    //==================== 删除 ===========================


    //===================== end 删除 =============================
}
