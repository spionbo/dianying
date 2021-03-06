Vue.mixin({
	filters:{
		formatTime( str ){
			try{
				return str.substr(0,10);
			}catch(e){
				return str||"-";
			}
		}
	},
	methods : {
		_letter( val ){ //字母不能小于6位
			return new RegExp("^[a-z|A-Z]{5,20}$").test(val);
		},
		_numAndLetter( val ){ //包含数字和字母且开头是字每
			if(val.length<5||val.length>20) return false;
			return (new RegExp("[a-z|A-Z]+").test(val) && new RegExp("\\d+").test(val));
		},
		_chinese( val ){
			let reg = /^[\u4e00-\u9fa5]{1,9}$/;
			if(reg.test(val)) return true;
			return false;
		},
		_path(val){
			let reg = /^([\/|\w|\-|\:])+/,
				result = val.match(reg);
			if(reg.test(val)) return true;
			return false;
		},
		_http : function( value ){
			let reg = /^(http|https):\/\/[\w|\:|\d|\/]+$/;
			return value.search(reg)>=0;
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
        },
		_del(obj){ //列表删除提示
			let [self,tag] = [this,$(obj.$event.currentTarget)];
			this.$pop({
				title : "删除",
				close : true,
				content : "确定要删除“"+obj.name+"”吗？",
				footer : {
					ok : "确定",
					cancel : "取消",
					okCallback : function(){
						let self = this;
						self.ajax({
							url : obj.url,
							type : "post",
							load : true,
							data : obj.data
						}).then(data=>{
							self.$closePop();
							self.$tips({
								content: "删除成功!"
							});
							obj.update();
						});
					}
				}
			});
		}
	}
});