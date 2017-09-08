import './cookie.js';
/*import requirePop from '../pop/requirePop.js';*/
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
	,androidUrl : 'http://a.app.qq.com/o/simple.jsp?pkgname=com.times.financial'
	,iosUrl : 'https://itunes.apple.com/cn/app/贸金所/id1188622429?mt=8'
	,downApp : 'http://a.app.qq.com/o/simple.jsp?pkgname=com.times.financial'
	,imgsrc : 'http://www.51mjs.com'
	//,duorongHttp : 'http://192.168.1.248'
	//,imgsrc : 'http://192.168.0.245:8888'
	,info : {} //用户信息
	,init : function( obj ){
		$.extend( T , obj );
		T.setImgSize();
		T.mobile();
		window.onresize = T.setImgSize;
	}
	,TONGJI : function(){
		clearTimeout(T.TONGJITIME);
		T.TONGJITIME = setTimeout(function(){
			var _hmt = _hmt || [];
			var hm = document.createElement("script");
			hm.src = "/js/tongji.js";
			var s = document.getElementsByTagName("body")[0];
			s.appendChild(hm);
		},300)
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
		/*var b=parseInt(n).toString();
		var len=b.length;
		if(len<=3){return b;}
		var r=len%3;
		return r>0?b.slice(0,r)+","+b.slice(r,len).match(/\d{3}/g).join(","):b.slice(r,len).match(/\d{3}/g).join(",");
	*/
	}
	,goURL : function(){
		const goURL = this.$route.query.goURL;
		function getParams( name ){
			let reg = new RegExp("(^|&|/?)" + name + "=([^&]*)(&|$)", "i");
			let r = encodeURI(goURL).substr(1).match(reg);
			if (r != null) return unescape(r[2]); return null;
		}
		if(goURL){
			let name = goURL.match(/\w+/)[0] ,
				returnUrl = getParams('returnUrl') ,
				reg = getParams('reg'),
				index = this.$route.query.index;

			returnUrl = returnUrl || null;
			T.go[name]({returnUrl:returnUrl,reg:reg, index:index})
		}
		//以传参的方式进来
		// web/index.html?goURL=
		//例如去登录页 ?goURL=login 如果未登录，我会自动跳到注册页
		//例如去登录完成后想去我的帐户 ?goURL=login?returnUrl=myaccount 如果未登录，我会自动跳到注册页
		//例如去注册页 ?goURL=login?reg=true
		//例如去推荐项目 ?goURL=recommend
		//例如去我的帐户 ?goURL=myaccount 没有登录，会先去登录页，完成登录后跳回我的帐户
	}
	,update : function(){
		T.width = T.body.width();
		T.height = T.body.height();
	},
	closePop(){
		$("#app").next().next().remove();
	}
	,addScript(src,callback){
		let head = document.head,
			s = document.createElement("script");
		s.src = src;
		s.onload = function() {
			callback();
		};
		head.insertBefore( s, head.firstChild );
	}
	,ajax : function( obj , bizType ,_data ){
		let data = JSON.stringify({//JSON.stringify(
			"version": "1.0",
			"appVersion": "HTML5",
			"requestTime": new Date().getTime(),
			"customerId": T.info.customerId, //用户登录ID
			"deviceId": "1",
			"bizType":bizType,
			"token": T.info.token,
			"data":obj
		});

		//if(obj)

		//其他是什么就传什么。  比如requestTime就传请求当时的时间戳， customerId传登陆后存在cookie的customerId，token也是
		//写死的只有 version，appVersion  appName  deviceId


		data = _data || data;
		return new Promise((resolve, reject) =>{
			$.ajax({
				url : '/api/service' ,
				type : 'POST',
				dataType : 'json',
				contentType:'application/json',
				processData : false,
				data : data,
				success : function(data){
					if(data.responseCode=="0000"){ //更新登录信息
						if($.fn.cookie('islogin')){
							T.setLoginInfo();
						}
						resolve(data);
					}else{
						if(obj.callback){
							return resolve(data);
						}else if(data.responseCode=="0001"){ //未登录
							localStorage.removeItem('userInfo');
							$.fn.cookie('islogin',null);
							T.go.login({reg:"true"});
						}
						else{
							/*requirePop('ajaxerror',{
								props : {
									transition : 'rotate3d',//rotate3d , slideOutUp , slideOutDown , bounceIn , flipInX , flipInY , fadeIn
									title : '错误',
									content : data.responseMsg,
									okTxt : '确定',
									closeCallback:function(){
										T.go.recommend();
										T.closePop();
									}
								}
							},"publicTips")*/
						}
					}
				},
				error : function(){

					if(obj.error) return obj.error();

					/*requirePop('ajaxerror',{
						props : {
							transition : 'rotate3d',//rotate3d , slideOutUp , slideOutDown , bounceIn , flipInX , flipInY , fadeIn
							title : '接口请求失败',
							content : '接口返请求失败，或接口不存在，请联系管理员。',
							okTxt : '确定',
							closeCallback:function(){
								T.closePop();
							}
						}
					},"publicTips");*/
				}
			});
		})
	}
}
module.exports = T;