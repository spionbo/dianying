import VueRouter from 'vue-router';
import backstage from "./module/backstage";
import novel from "./module/novel";
import elasticsearch from "./module/elasticsearch";
window.router = new VueRouter({
    mode: 'hash',
    //mode: 'history',
    base: __dirname,
    linkActiveClass: "active",
    routes: [
        //{ path: '/', redirect:'/main'},
        {
            path: '/login',
            components: {login: () => import('../module/login')},
        },
        {
            path: '/main',
            component: () => import('../module')
        },
        backstage, //后台管理
        novel, //小说管理
        elasticsearch,
        // catch all redirect
        {path: '*', redirect: '/main'}
    ]
});
