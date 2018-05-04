/**
 * 搜索
 * created by bo.peng
 * email:spion@qq.com
 */
module.exports={
    path: "/elasticsearch",
    component: {template: '<router-view></router-view>'},
    children: [
        {path: ''},
        {
            path: "xiaoshuo",
            component: {template: '<router-view></router-view>'},
            children: [
                {path: '', redirect: 'list'},
                {
                    path: "list",
                    component: () => import('../../module/elasticsearch/xiaoshuo/list.vue')
                }
            ]
        }
    ]
}
