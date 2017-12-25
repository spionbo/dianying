export default {
	methods : {
		_chinese( val ){
			let reg = /^[\u4e00-\u9fa5]{1,9}$/;
			if(reg.test(val)) return true;
			return false;
		},
		_path(val){
			let reg = /^(\/\w)+/,
				result = val.match(reg);
			if(reg.test(val)) return true;
			return false;
		},
		_http : function( value ){
			var reg = /^(http|https):\/\/([\w-]+\.)+[\w-]+(\/[\w-./?%&=]*)?$/;
			return value.search(reg)>=0
		},
        _verificationForm(){
            let [self,ischeck] = [this,true];
            this.$children.map(obj=>{
                obj.verification(function( b ){
                    if(!b){ischeck = b};
                });
            });
            if(ischeck){
                return true;
            }
            return false;
        }
	}
}