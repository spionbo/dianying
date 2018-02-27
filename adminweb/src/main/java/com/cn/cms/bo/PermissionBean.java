package com.cn.cms.bo;

import com.cn.cms.po.Permission;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * Created by Administrator on 2017/11/17 0017.
 */
@Getter
@Setter
public class PermissionBean {

    private Permission permission;

    private List<PermissionBean> permissionBeans;
}
