package com.cn.cms.contants;

/**
 * Created by Administrator on 2018/1/12 0012.
 * 所有权限名称
 */
public class PermissionNames {

    //后台栏目管理  用于控制栏目显示
    public static class BACKSTAGE{
        private static final String name = "backstage";
        public static final String READ = name+":read";
        public static final String UPDATE = name+":update";
        public static final String WRITE = name+":write";
        public static final String DELETE = name+":delete";

        //栏目管理
        public static class COLUMN{

            private static final String name = BACKSTAGE.name+"Column";
            //查看栏目
            public static final String READ = name+":read";
            //删除栏目
            public static String DELETE = name+"Delete:delete";
            //更新栏目
            public static String UPDATE = name+"Update:update";
            //新增栏目
            public static class ADD{//如果只有 read 则只能看，不能写
                private static final String name = COLUMN.name+"Add";
                //查看新增栏目
                public static final String READ = name+":read";
                //新增
                public static final String WRITE = name+":write";
            }
            //查看栏目列表
            public static String LIST = name+"List:read";
        }
        //管理员管理
        public static class ADMIN{
            private static final String name = BACKSTAGE.name+"Admin";
            //查看管理员栏目
            public static final String READ = name+":read";
            //删除管理员
            public static String DELETE = name+"Delete:delete";
            //更新管理员
            public static String UPDATE = name+"Update:update";
            //新增管理员
            public static class ADD{
                private static final String name = ADMIN.name+"Add";
                //查看新增管理员
                public static final String READ = name+":read";
                public static final String WRITE = name+":write";
            }
            //查看列表
            public static String LIST = name+"List:read";
        }
        //图片管理
        public static class IMG{
            private static final String name = BACKSTAGE.name+"Img";
            //查看图片管理栏目
            public static final String READ = name+":read";

            //删除图片
            public static String DELETE = name+"Delete:delete";
            //更新图片
            public static String UPDATE = name+"Update:update";
            //删除图片分类
            public static String DELETECLASS = name+"DeleteClass:delete";
            //更新图片分类
            public static String UPDATEClass = name+"UpdateClass:update";
            //查看图片列表
            public static String LIST = name+"List:read";
            //查看图片分类列表
            public static String CLASSLIST = name+"ClassList:read";

            //新增栏目
            public static class ADD{
                private static final String name = IMG.name+"Add";
                //查看
                public static final String READ = name+":read";
                //写
                public static final String WRITE = name+":write";
            }
            //图片基本信息
            public static class BASIC{
                private static final String name = IMG.name+"Basic";
                //查看
                public static final String READ = name+":read";
                //写
                public static final String WRITE = name+":write";
            }
        }
        //管理员管理
        public static class PERMISSION{
            private static final String name = BACKSTAGE.name+"Permission";
            //读
            public static final String READ = name+":read";
            //读列表
            public static final String LIST = name+"List:read";
            //删
            public static String DELETE = name+"Delete:delete";
            //更
            public static String UPDATE = name+"Update:update";

            //新增权限
            public static class ADD{
                private static final String name = PERMISSION.name+"Add";
                //读
                public static final String READ = name+":read";
                //写
                public static final String WRITE = name+":write";
            }
        }
    };
    //上传管理
    public static class UPLOAD{
        private static final String name = "upload";

        //图片上传
        public static class IMG{
            public static final String WRITE = name+":write";
        }
    }
    //小说
    public static class XIAOSHUO{
        private static final String name = "xiaoshuo";
        //查看小说
        public static final String READ = name+":read";

        //新增小说
        public static final String ADD = name+"Add:write";
        //修改小说
        public static final String UPDATE = name+"Update:update";
        //查看小说列表
        public static final String LIST = name+"List:read";
        //删除小说列表
        public static final String DELETE = name+"delete:delete";


        //新增分类
        public static final String ADDCLASS = name+"AddClass:write";
        //修改分类
        public static final String UPDATECLASS = name+"UpdateClass:update";
        //删除分类
        public static final String DELETECLASS = name+"DeleteClass:delete";
        //查看分类列表
        public static final String CLASSLIST = name+"ClassList:read";

    }
}
