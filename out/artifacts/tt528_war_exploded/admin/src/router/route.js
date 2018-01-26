module.exports = function(to, from){
	let self = this;
	if(to.path == '/main' && !this.isLogin){
		router.push('/login');
	}
};