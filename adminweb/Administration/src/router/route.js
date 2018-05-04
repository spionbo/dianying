module.exports = function(to, from){
	if(to.path == '/main' && !this.isLogin){
		router.push('/login');
	}
};
