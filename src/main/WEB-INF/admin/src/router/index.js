window.router = new VueRouter({
	mode: 'hash',
	//mode: 'history',
	base: __dirname,
	linkActiveClass: "active",
	routes: [
		{ path: '/', redirect:'/main'},
		{ 
			path : '/login',
			components: { login : ()=>import('../module/login')},
		},
		{
			path : '/main', 
			components: { default : ()=>import('../module')}
		},
		// catch all redirect
		{ path: '*', redirect: '/' }
	]
});