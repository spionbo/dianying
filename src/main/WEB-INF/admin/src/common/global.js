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
	for (let k in o)
		if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
	return fmt;
}
let T = {
	body : $('body')
	//,duorongHttp : 'http://192.168.1.248'
	//,imgsrc : 'http://192.168.0.245:8888'
	,info : {} //用户信息
	,init : function( obj ){
		$.extend( T , obj );
		T.mobile();
		window.onresize = T.setImgSize;
	}
	,setLoginInfo : function(){
		document.cookie = "islogin=true; expires="+new Date(new Date().getTime()+30*60*1000)+";path=/"
	}
	,mobile : function(){
		let ua = navigator.userAgent,
			uaLower = ua.toLowerCase(),
			IS_IPAD = ua.match(/iPad/i) != null,
			IS_IPHONE = !IS_IPAD && ((ua.match(/iPhone/i) != null) || (ua.match(/iPod/i) != null)),
			IS_IOS = IS_IPAD || IS_IPHONE,
			IS_ANDROID = !IS_IOS && ua.match(/android/i) != null,
			IS_MOBILE = IS_IOS || IS_ANDROID ;

		T.Iphone = IS_IOS;
		T.Android = IS_ANDROID;
		T.Mobile = IS_MOBILE

		function is_weixin() {
			let ua = uaLower;
			if (ua.match(/micromessenger/i) == "micromessenger") {
				return true;
			} else {
				return false;
			}
		}
		function is_weixin_ios() {
			let ua = uaLower;
			if (ua.match(/micromessenger/i) == "micromessenger" && T.Iphone )
			{ return true; } else { return false; }
		};
		function is_weibo() {
			let ua = uaLower;
			if (ua.match(/Weibo/i) == "weibo")
			{ return true; } else { return false; }
		};
		function is_qq() {
			let ua = uaLower;
			if (ua.match(/QQ\//i) == "qq/")
			{ return true; } else { return false; }
		};
		T.weixin = is_weixin();
		T.ios_weixin = is_weixin_ios();
		T.weibo = is_weibo();
		T.qq = is_qq();
	},
	setItem : function( key , value , obj ){
		localStorage.setItem(key,JSON.stringify(value));
	},
	getItem : function( name ){
		return JSON.parse(localStorage.getItem(name));
	},
	toFixed( num ){
		let text = String(num).match(/\d*[\.|\d]{3}/);
		if(text){
			text = text[0];
		}else{
			text = num+'.00';
		}
		return text;
	},
	formatTime( str ){
		return str.substr(0,10);
	},
	toNumber : function( num ){ //把所有非数字的删除
		let val = String(num).match(/[\d]*\.{0,1}\d{0,2}/);
		if(!val){
			num = num.replace(val[0],'');
		}else{
			return val[0];
		}
		return num;
	}
	,justNumber : function( num , callback){ //只能是数字
		let val = String(num).match(/[^\d]/);
		if(val){
			num = num.replace(val[0],'');
		}
		return num;
	}
	,getEvent : function(e, def , p){
		e = e || window.event;
		if(!def){
			e.stopPropagation()
		}
		if(!p){
			e.preventDefault()
		}
		e = e.touches ? e.touches[0] : e;
		return e;
	}
	,format_number(n){ //金额加“逗号”
		return parseFloat(n).toLocaleString()
	}
	,update : function(){
		T.width = T.body.width();
		T.height = T.body.height();
	},
	getQueryString : function( name ){
		var reg = new RegExp("(^|&|/?)" + name + "=([^&]*)(&|$)", "i");
		var r = encodeURI(window.location.search||window.location.href||window.location.hash).substr(1).match(reg);
		if (r != null) return unescape(r[2]); return null;
	},
	ajax : function( obj ){
		obj.type = obj.type || 'GET';
		obj.url = '/webapi'+obj.url;
		return new Promise((resolve, reject) =>{
			$.ajax({
				url : obj.url ,
				type : obj.type,
                dataType : 'json',
                data : obj.data,
				success : function(data){
					if(data.code==0){ //更新登录信息
						resolve(data);
					}else if(data.code==-110){
						Main.setLoginStatus(false);
					}else{
						if(obj.callback){
							return resolve(data);
						}
					}
				},
				error : function(){
					if(obj.error) return obj.error();
				}
			});
		});
	}
}
module.exports = T;