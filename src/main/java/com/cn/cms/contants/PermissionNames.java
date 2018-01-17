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
            private static final String name = BACKSTAGE.name+"Img";
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
                private static final String name = IMG.name+"AddClass";
                public static final String READ = name+":read";
                public static final String UPDATE = name+":update";
                public static final String WRITE = name+":write";
                public static final String DELETE = name+":delete";
            }
            //分类列表
            public static class CLASS_LIST{
                private static final String name = IMG.name+"ClassList";
                public static final String READ = name+":read";
                public static final String UPDATE = name+":update";
                public static final String WRITE = name+":write";
                public static final String DELETE = name+":delete";
            }
            //基本信息
            public static class BASIC{
                private static final String name = IMG.name+"Basic";
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

            //新增
            public static class ADD{
                private static final String name = Permission.name+"Add";
                public static final String READ = name+":read";
                public static final String UPDATE = name+":update";
                public static final String WRITE = name+":write";
                public static final String DELETE = name+":delete";
            }
            //列表
            public static class LIST{
                private static final String name = Permission.name+"List";
                public static final String READ = name+":read";
                public static final String UPDATE = name+":update";
                public static final String WRITE = name+":write";
                public static final String DELETE = name+":delete";
            }
            //查看我的权限
            public static class VIEW{
                private static final String name = Permission.name+"view";
                public static final String READ = name+":read";
                public static final String UPDATE = name+":update";
                public static final String WRITE = name+":write";
                public static final String DELETE = name+":delete";
            }
        }
    };
    //上传管理
    public static class UPLOAD{
        private static final String name = "upload";

        //图片上传
        public static class IMG{
            public static final String name = UPLOAD.name+"Img";
            public static final String WRITE = name+":write";
            public static final String UPDATE = name+":update";
        }
    }
    //小说管理
    public static class XIAOSHUO{
        private static final String name = "xiaoshuo";
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
            private static final String name = XIAOSHUO.name+"AddClass";
            public static final String READ = name+":read";
            public static final String UPDATE = name+":update";
            public static final String WRITE = name+":write";
            public static final String DELETE = name+":delete";
        }
        //分类列表
        public static class CLASS_LIST{
            private static final String name = XIAOSHUO.name+"ClassList";
            public static final String READ = name+":read";
            public static final String UPDATE = name+":update";
            public static final String WRITE = name+":write";
            public static final String DELETE = name+":delete";
        }

    }
}
