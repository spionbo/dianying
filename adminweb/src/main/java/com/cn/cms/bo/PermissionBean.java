package com.cn.cms.bo;

import com.cn.cms.po.Permission;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * 栏目转换 2018/5/10
 * [
 *     {
 *         name : "栏目名称",
 *         id : 1,
 *         list : [
 *             {
 *                 name : "二级栏目名称",
 *                 id : 1,
 *                 list : [
 *                     {
 *                         name : "三级栏目名称",
 *                         id : 1,
 *                         list : [
 *                              name : "四级栏目名称",
 *  *                           id : 1,
 *                         ]
 *                     }
 *                 ]
 *             },
 *             {
 *                 name : "二级栏目名称",
 *                 id : 1,
 *             }
 *         ]
 *     },
 *     {
 *         name : "栏目名称",
 *         id : 1,
 *         list : [
 *             {
 *                 name : "二级栏目名称",
 *                 id : 1,
 *             }
 *         ]
 *     }
 * ]
 */
@Getter
@Setter
public class PermissionBean extends Permission {
    //子类，子栏目
    private List<PermissionBean> list;

    public void Permission( Permission permission){
        setPermission(permission);
    }

    /**
     * 设置参数
     * @param permission
     */
    public void setPermission(Permission permission){
        this.setId(permission.getId());
        this.setName(permission.getName());
        this.setParentId(permission.getParentId());
        this.setSort(permission.getSort());
        this.setUrl(permission.getUrl());
        this.setDescription(permission.getDescription());
        this.setPowerKey(permission.getPowerKey());
        this.setPowerVal(permission.getPowerKey());
        this.setCreateUserName(permission.getCreateUserName());
        this.setLastModifyUserName(permission.getLastModifyUserName());
        this.setCreateTime(permission.getCreateTime());
        this.setCreateTimeStr(permission.getCreateTimeStr());
        this.setCreateUserId(permission.getCreateUserId());
        this.setDelTag(permission.getDelTag());
        this.setLastModifyUserId(permission.getLastModifyUserId());
        this.setUpdateTime(permission.getUpdateTime());
        this.setUpdateTimeStr(permission.getUpdateTimeStr());
    }
}