import Vue from "vue";
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
		_chinese( val ){
			let reg = /^[\u4e00-\u9fa5]{1,9}$/;
			if(reg.test(val)) return true;
			return false;
		},
		_letterOrChinese( val ){ //字母或中文
			return new RegExp("^[a-z|A-Z|\u4e00-\u9fa5]{1,9}$").test(val);
		},
		_numAndLetter( val ){ //包含数字和字母且开头是字每
			if(val.length<5||val.length>20) return false;
			if(!(/^[a-z|A-Z]{1,}/.test(val))) return false;
			return (new RegExp("[a-z|A-Z]+").test(val) && new RegExp("\\d+").test(val));
		},
		_numOrLetter( val ){ //包含数字和字母
			if(val.length<5||val.length>20) return false;
			if(!(/^[a-z|A-Z]{1,}/.test(val))) return false;
			return (new RegExp("[\\w|\\d]+").test(val));
		},
		_password(val){
			return this._numAndLetter(val);
		},
		_path(val){
			let reg = /^([\/|\w|\-|\:])+/;
				//result = val.match(reg);
			if(reg.test(val)) return true;
			return false;
		},
		_http : function( value ){
			let reg = /^(http|https):\/\/[\w|\:|\d|\/]+$/;
			return value.search(reg)>=0;
		},
        _verificationForm(){
            let [ischeck] = [true];
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
			//let [self,tag] = [this,$(obj.$event.currentTarget)];
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
							obj.callback && obj.callback();
							//window.location.reload();
							obj.update();
						});
					}
				}
			});
		}
	}
});
