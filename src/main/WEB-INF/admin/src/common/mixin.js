export default {
	methods : {
		_chinese( val ){
			let reg = /^[\u4e00-\u9fa5]{1,9}$/;
			if(reg.test(val)) return true;
			return false;
		},
		_path(val){
			let reg = /^\/\w+/,
				result = val.match(reg);
			if(reg.test(val)) return true;
			return false;
		},
		_englishAndNumber(){

		}
	}
}