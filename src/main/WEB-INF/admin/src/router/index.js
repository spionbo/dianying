window.router = new VueRouter({
	mode: 'hash',
	//mode: 'history',
	base: __dirname,
	linkActiveClass: "active",
	routes: [
		//{ path: '/', redirect:'/main'},
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
		{//后台管理
			path : "/backstage",
			component: {template:'<router-view></router-view>'},
			children:[
				{ path: '', redirect: 'column/list' },
				{
					path : "column",
					component : {template:'<router-view></router-view>'},
					children:[
						{ path: '', redirect: 'list' },
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
				{
					path : "admin",
					component : {template:'<router-view></router-view>'},
					children:[
						{ path: '', redirect: 'list' },
						{
							path : "add",
							component : ()=>import(/* webpackChunkName: "admin-add" */ '../module/admin/add.vue')
						},
						{
							path : "list",
							component : ()=>import(/* webpackChunkName: "admin-list" */ '../module/admin/list.vue')
						}
					]
				},
				{
					path : "image",
					component : {template:'<router-view></router-view>'},
					children:[
						{ path: '', redirect: 'list' },
						{
							path : "classifyAdd",
							component : ()=>import('../module/image/classifyAdd.vue')
						},
						{
							path : "classifyList",
							component : ()=>import('../module/image/classifyList.vue')
						},
						{
							path : "add",
							component : ()=>import('../module/image/add.vue')
						},
						{
							path : "list",
							component : ()=>import('../module/image/list.vue')
						},
						{
							path : "search",
							component : ()=>import('../module/image/search.vue')
						}
					]
				}
			]
		},
		{
			path : "/novel",
			component: {template:'<router-view></router-view>'},
			children:[
				{ path: '', redirect: 'list' },
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
		{ path: '*', redirect: '/main' }
	]
});
