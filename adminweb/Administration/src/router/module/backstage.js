/**
 * 后台管理
 * created by bo.peng
 * email:spion@qq.com
 */
module.exports={
    path: "/backstage",
    component: {template: '<router-view></router-view>'},
    children: [
        {path: '', redirect: 'column/list'},
        {
            path: "column",
            component: {template: '<router-view></router-view>'},
            children: [
                {path: '', redirect: 'list'},
                {
                    path: "add",
                    component: () => import(/* webpackChunkName: "column-add" */ '../../module/column/add.vue')
                },
                {
                    path: "list",
                    component: () => import(/* webpackChunkName: "column-list" */ '../../module/column/list.vue')
                }
            ]
        },
        {
            path: "admin",
            component: {template: '<router-view></router-view>'},
            children: [
                {path: '', redirect: 'list'},
                {
                    path: "add",
                    component: () => import(/* webpackChunkName: "admin-add" */ '../../module/admin/add.vue')
                },
                {
                    path: "list",
                    component: () => import(/* webpackChunkName: "admin-list" */ '../../module/admin/list.vue')
                }
            ]
        },
        {
            path: "image",
            component: {template: '<router-view></router-view>'},
            children: [
                {path: '', redirect: 'list'},
                {
                    path: "classifyAdd",
                    component: () => import('../../module/image/addClassify.vue')
                },
                {
                    path: "classifyList",
                    component: () => import('../../module/image/classifyList.vue')
                },
                {
                    path: "add",
                    component: () => import('../../module/image/add.vue')
                },
                {
                    path: "list",
                    component: () => import('../../module/image/list.vue')
                },
                {
                    path: "search",
                    component: () => import('../../module/image/search.vue')
                },
                {
                    path: "addImagesBase",
                    component: () => import('../../module/image/addImagesBase.vue')
                }
            ]
        },
        {
            path: "permission",
            component: {template: '<router-view></router-view>'},
            children: [
                {path: '', redirect: 'list'},
                {
                    path: "add",
                    component: () => import('../../module/permission/add.vue')
                },
                {
                    path: "list",
                    component: () => import('../../module/permission/list.vue')
                },
                {path: "myPermission", redirect: "/backstage/column/list"},
                {path: "adminPower", redirect: "/backstage/admin/list"}
            ]
        }
    ]
};
