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
	justNumber( num , callback){ //只能是数字
		let val = String(num).match(/[^\d]/);
		if(val){
			num = num.replace(val[0],'');
		}
		return num;
	},
	formatTime( str ){
		return str.substr(0,10);
	},
	getQueryString : function( name ){
		var reg = new RegExp("(^|&|/?)" + name + "=([^&]*)(&|$)", "i");
		var r = encodeURI(window.location.search||window.location.href||window.location.hash).substr(1).match(reg);
		if (r != null) return unescape(r[2]); return null;
	},
}
module.exports = T;