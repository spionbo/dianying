let MyPlugin = function(){

};
MyPlugin.install = function (Vue, options) {
	Object.assign(Vue.prototype,{
		ajax( obj ){
			let self = this;
			obj.type = obj.type || 'GET';
			obj.url = '/webapi'+obj.url;
			return new Promise((resolve, reject) =>{
				if(obj.load) self.$pops.loadding();
				$.ajax({
					url : obj.url ,
					type : obj.type,
					dataType : 'json',
					data : obj.data,
					success : function(data){
						if(data.code==0){ //更新登录信息
							resolve(data);
						}else{
							if(obj.callback){
								return resolve(data);
							}else{
								self.$closePop();
								self.$tips({
									transition : "shake-vertical",
									content : data.message,
									time : 2000
								});
							}
						}
						if(obj.load) self.$pops.removeLoadding();
					},
					error : function(){
						if(obj.error) return obj.error();
					}
				});
			});
		}
	});
};
export default MyPlugin;