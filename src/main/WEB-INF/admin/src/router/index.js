window.router = new VueRouter({
	mode: 'hash',
	//mode: 'history',
	base: __dirname,
	linkActiveClass: "active",
	routes: [
		{ path: '/', redirect:'/login'},
		{ path : '/login', component:()=>import('../module/login') },
		{ path : '/main', component:()=>import('../module') },
		// catch all redirect
		{ path: '*', redirect: '/' }
	]
});