import Vue from "vue";
import T from "../../common/global";
let MyPlugin = function () {
	T.init();
	Date.prototype.Format = function (fmt) { //author: meizz
		let o = {
			"M+": this.getMonth() + 1, //月份
			"d+": this.getDate(), //日
			"h+": this.getHours(), //小时
			"m+": this.getMinutes(), //分
			"s+": this.getSeconds(), //秒
			"q+": Math.floor((this.getMonth() + 3) / 3), //季度
			"S": this.getMilliseconds() //毫秒
		};
		if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
		for (let k in o){
			if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
		}
		return fmt;
	};
};
MyPlugin.install = function (Vue, options) {
	Object.assign(Vue, {
	});
	Object.assign(Vue.prototype, {
		ajax(obj) {
			let self = this;
			obj.type = obj.type || 'GET';
			obj.data = obj.data || {};
			if (obj.load) self.$pops.loadding();
			return new Promise((resolve, reject) => {
				$.ajax({
					url: obj.url,
					type: obj.type,
					dataType: obj.dataType || 'json',
					data: obj.data,
					timeout: 500000,
					success: function (data) {
                        if(data.code==0){ //更新登录信息
                            resolve(data);
                        }else if(data.code==-110){
                            self.$tips({
                                transition : "shake-vertical",
                                content : data.message,
                                time : 2000
                            });
                            Main.setLoginStatus(false);
                        }else{
                            if(obj.callback){
                                resolve(data);
                            }else{
                                self.$closePop && self.$closePop();
                                self.$tips({
                                    transition : "shake-vertical",
                                    content : data.message,
                                    time : 2000
                                });
                            }
                        }
                        if(obj.load) self.$pops.removeLoadding();
					},
					error: function () {
						if (obj.error) {
							obj.error();
						} else {
							self.$pop({
								title: "请求接口超时",
								close: true,//是否显示关闭按钮
                                icon : "icon2",
								content: "请重试，或联系客服！"
							});
						}
						if (obj.load) self.$pops.removeLoadding();
					}
				});
			})
		},
		setImgSize : T.setImgSize
	});
};
MyPlugin();
Vue.use(MyPlugin);
