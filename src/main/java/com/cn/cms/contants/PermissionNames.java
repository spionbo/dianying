package com.cn.cms.contants;

import com.cn.cms.biz.PermissionBiz;
import com.cn.cms.bo.PermissionBean;
import com.cn.cms.middleware.JedisClient;
import com.cn.cms.po.Permission;
import com.cn.cms.service.PermissionSevice;
import com.cn.cms.service.impl.PermissionSeviceImpl;
import com.cn.cms.utils.CommonUtil;
import com.cn.cms.utils.StringUtils;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Administrator on 2018/1/12 0012.
 * 所有权限名称
 */
public class PermissionNames {

    /**
     * 存储key val 权限字段
     */
    public static Map<String,Integer> map;

    /**
     * 所有栏目
     */
    private static List<Permission> allColumn;

    /**
     * 所有栏目树状
     */
    private static List<PermissionBean> allColumnBean;

    public static void init(List<Permission> list , List<PermissionBean> permissionBeans){
        allColumn = list;
        allColumnBean = permissionBeans;
        update();
    }

    /**
     * 更新栏目
     */
    public static void update(){
       setControllerPower();
    }

    public static List<Permission> getAllcolumn(){
        return allColumn;
    }

    public static List<PermissionBean> getAllcolumnPermissionBeans(){
        return allColumnBean;
    }

    /**
     * 设计接口请求权限
     */
    private static void setControllerPower(){
        Map<String,Integer> map = new HashMap<>();
        List<Permission> list = getAllcolumn();
        for (int i=0;i<list.size();i++){
            Permission permission = list.get(i);
            map.put(permission.getPowerKey(),permission.getPowerVal());
        }
        PermissionNames.map = map;
    }

    //后台栏目管理  用于控制栏目显示
    public static class BACKSTAGE{
        private static final String name = "Backstage";
        public static final String READ = name+":read";
        public static final String UPDATE = name+":update";
        public static final String WRITE = name+":write";
        public static final String DELETE = name+":delete";

        //栏目管理
        public static class COLUMN{
            private static final String name = BACKSTAGE.name+"Column";
            public static final String READ = name+":read";
            public static final String UPDATE = name+":update";
            public static final String WRITE = name+":write";
            public static final String DELETE = name+":delete";

            //新增栏目
            public static class ADD{
                private static final String name = COLUMN.name+"Add";
                public static final String READ = name+":read";
                public static final String UPDATE = name+":update";
                public static final String WRITE = name+":write";
                public static final String DELETE = name+":delete";
            }
            //栏目列表
            public static class LIST{
                private static final String name = COLUMN.name+"List";
                public static final String READ = name+":read";
                public static final String UPDATE = name+":update";
                public static final String WRITE = name+":write";
                public static final String DELETE = name+":delete";
            }
        }
        //管理员管理
        public static class ADMIN{
            private static final String name = BACKSTAGE.name+"Admin";
            public static final String READ = name+":read";
            public static final String UPDATE = name+":update";
            public static final String WRITE = name+":write";
            public static final String DELETE = name+":delete";

            //新增
            public static class ADD{
                private static final String name = ADMIN.name+"Add";
                public static final String READ = name+":read";
                public static final String UPDATE = name+":update";
                public static final String WRITE = name+":write";
                public static final String DELETE = name+":delete";
            }
            //列表
            public static class LIST{
                private static final String name = ADMIN.name+"List";
                public static final String READ = name+":read";
                public static final String UPDATE = name+":update";
                public static final String WRITE = name+":write";
                public static final String DELETE = name+":delete";
            }
        }
        //图片管理
        public static class IMG{
            private static final String name = BACKSTAGE.name+"Image";
            public static final String READ = name+":read";
            public static final String UPDATE = name+":update";
            public static final String WRITE = name+":write";
            public static final String DELETE = name+":delete";

            //列表
            public static class LIST{
                private static final String name = IMG.name+"List";
                public static final String READ = name+":read";
                public static final String UPDATE = name+":update";
                public static final String WRITE = name+":write";
                public static final String DELETE = name+":delete";
            }
            //新增分类
            public static class ADD_CLASS{
                private static final String name = IMG.name+"ClassifyAdd";
                public static final String READ = name+":read";
                public static final String UPDATE = name+":update";
                public static final String WRITE = name+":write";
                public static final String DELETE = name+":delete";
            }
            //分类列表
            public static class CLASS_LIST{
                private static final String name = IMG.name+"ClassifyList";
                public static final String READ = name+":read";
                public static final String UPDATE = name+":update";
                public static final String WRITE = name+":write";
                public static final String DELETE = name+":delete";
            }
            //基本信息
            public static class BASIC{
                private static final String name = IMG.name+"AddImagesBase";
                public static final String READ = name+":read";
                public static final String UPDATE = name+":update";
                public static final String WRITE = name+":write";
                public static final String DELETE = name+":delete";
            }
        }
        //权限管理
        public static class Permission{
            private static final String name = BACKSTAGE.name+"Permission";
            public static final String READ = name+":read";
            public static final String UPDATE = name+":update";
            public static final String WRITE = name+":write";
            public static final String DELETE = name+":delete";

            //列表
            public static class LIST{
                private static final String name = Permission.name+"List";
                public static final String READ = name+":read";
                public static final String UPDATE = name+":update";
                public static final String WRITE = name+":write";
                public static final String DELETE = name+":delete";
            }
            //查看我的权限
            public static class MY_PEMISSION{
                private static final String name = Permission.name+"MyPermission";
                public static final String READ = name+":read";
                public static final String UPDATE = name+":update";
                public static final String WRITE = name+":write";
                public static final String DELETE = name+":delete";
            }
            //管理员权限
            public static class ADMIN_POWER{
                private static final String name = Permission.name+"AdminPower";
                public static final String READ = name+":read";
                public static final String UPDATE = name+":update";
                public static final String WRITE = name+":write";
                public static final String DELETE = name+":delete";
            }
        }
    };
    //小说管理
    public static class XIAOSHUO{
        private static final String name = "Novel";
        public static final String READ = name+":read";
        public static final String UPDATE = name+":update";
        public static final String WRITE = name+":write";
        public static final String DELETE = name+":delete";

        //新增
        public static class ADD{
            private static final String name = XIAOSHUO.name+"Add";
            public static final String READ = name+":read";
            public static final String UPDATE = name+":update";
            public static final String WRITE = name+":write";
            public static final String DELETE = name+":delete";
        }
        //列表
        public static class LIST{
            private static final String name = XIAOSHUO.name+"List";
            public static final String READ = name+":read";
            public static final String UPDATE = name+":update";
            public static final String WRITE = name+":write";
            public static final String DELETE = name+":delete";
        }
        //新增分类
        public static class ADD_CLASS{
            private static final String name = XIAOSHUO.name+"AddClassify";
            public static final String READ = name+":read";
            public static final String UPDATE = name+":update";
            public static final String WRITE = name+":write";
            public static final String DELETE = name+":delete";
        }
        //分类列表
        public static class CLASS_LIST{
            private static final String name = XIAOSHUO.name+"ClassifyList";
            public static final String READ = name+":read";
            public static final String UPDATE = name+":update";
            public static final String WRITE = name+":write";
            public static final String DELETE = name+":delete";
        }

    }
}
