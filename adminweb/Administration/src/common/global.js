import Jquery from "../../static/plug/jquery-1.10.2.min";
window.$ = Jquery;
let T = {
	body : $('body'),
	info : {}, //用户信息
	init : function( obj ){
		$.extend( T , obj );
	},
	setItem : function( key , value , obj ){
		localStorage.setItem(key,JSON.stringify(value));
	},
	getItem : function( name ){
		return JSON.parse(localStorage.getItem(name));
	}
}
export default T;
