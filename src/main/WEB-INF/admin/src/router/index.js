window.router = new VueRouter({
	mode: 'hash',
	//mode: 'history',
	base: __dirname,
	linkActiveClass: "active",
	routes: [
		{ path: '/', redirect:'/main'},
		{ 
			path : '/login',
			components: {login:()=>import('../module/login')},
		},
		{
			path : '/main', 
			component: ()=>import('../module')
		},
		{
			path : '/add',
			component : ()=>import(/* webpackChunkName: "column-add" */ '../module/column/add.vue')
		},
		{
			path : "/column",component: { template:'<router-view></router-view>'},
			children:[
				{ path: '', redirect: '/column/list' },
				{
					path : "add",
					component : ()=>import(/* webpackChunkName: "column-add" */ '../module/column/add.vue')
				},
				{
					path : "list",
					component : ()=>import(/* webpackChunkName: "column-list" */ '../module/column/list.vue')
				}
			]
		},
		// catch all redirect
		{ path: '*', redirect: '/' }
	]
});
