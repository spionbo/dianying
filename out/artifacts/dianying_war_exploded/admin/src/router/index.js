window.router = new VueRouter({
	mode: 'hash',
	//mode: 'history',
	base: __dirname,
	linkActiveClass: "active",
	routes: [
		{ path: '/', redirect:'/main'},
		{ path : '/main', component:()=>import('../module') },
		// catch all redirect
		{ path: '*', redirect: '/' }
	]
});