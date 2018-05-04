/**
 *
 * created by bo.peng
 * email:spion@qq.com
 */
module.exports={
    path: "/novel",
    component: {template: '<router-view></router-view>'},
    children: [
        {path: '', redirect: 'list'},
        {
            path: "add",
            component: () => import(/* webpackChunkName: "column-add" */ '../../module/xiaoshuo/add.vue')
        },
        {
            path: "list",
            component: () => import(/* webpackChunkName: "column-list" */ '../../module/xiaoshuo/list.vue')
        },
        {
            path: "addClassify",
            component: () => import('../../module/xiaoshuo/addClassify.vue')
        },
        {
            path: "classifyList",
            component: () => import('../../module/xiaoshuo/classifyList.vue')
        },
        {
            path: "chapterList",
            component: () => import('../../module/xiaoshuo/chapterList.vue')
        },
    ]
}
