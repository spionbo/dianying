package com.cn.cms.contants;

/**
 * Created by Administrator on 2018/1/12 0012.
 * 所有权限名称
 */
public class PermissionNames {

    //后台管理
    public static class BACKSTAGE{
        public static final String READ = "backstage:read";
        public static final String UPDATE = "backstage:update";
        public static final String WRITE = "backstage:write";
        public static final String DELETE = "backstage:delete";
    };

    //后台栏目管理
    public static class BACKSTAGE_COLUMN{
        public static final String READ = "backstage_column:read";
        public static final String UPDATE = "backstage_column:update";
        public static final String WRITE = "backstage_column:write";
        public static final String DELETE = "backstage_column:delete";
    }
}
