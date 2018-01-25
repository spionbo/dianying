<style>
</style>
<template>
	<div class="txt">
		<div class="edit" v-if="!disabled">
			<input
					v-if="type=='text'"
					type="text"
					:placeholder="placeholder"
					v-model="value"
					:maxlength="maxlength"
			>
			</input>
			<input
					v-if="type=='password'"
					type="password"
					:placeholder="placeholder"
					v-model="value"
					:maxlength="maxlength"
			>
			</input>
		</div>
		<div class="edit" v-else>
			<input
					v-if="type=='text'"
					type="text"
					:placeholder="placeholder"
					v-model="value"
					:maxlength="maxlength"
					disabled="disabled"
			>
			</input>
		</div>
		<div class="else">
			<slot name='else'></slot>
		</div>
		<div class="cnt" :class="{cur:error}">
			{{text}}
		</div>
	</div>
</template>
<script>
	const ERROR = {
		letter : {
			default : "请输入字母",
			error : "字母不能小于5位大于20位"
		},
		numAndLetter : {
			default : "请输入内容，内容必需包含字母和数字",
			error : "必需包含字母和数字相结合(6-20位)"
		},numOrLetter : {
			default : "请输入内容，内容为数字或字母",
			error : "内容为数字或字母，以字母开头(6-20位)"
		},
		chinese : {
			default : "请输入内容，不能大于5个中文字符",
			error : "内容必需为中文，不能大于5个中文字符"
		},
		path : {
			default : "例：\"/name\"",
			error : "路经输入错误。"
		},
        http : {
            default : "域名（http://www.xy.com）",
            error : "请输入正确的域名（例：http://www.xy.com）。"
        },
		password : {
			default : "请输入密码，密码为6位数以上必需包含英文和数字",
			error : "密码为6位数以上必需包含英文和数字"
		}
	};
	export default {
		props: {
			data : String,
			type : {
				type:String,
				required : true
			},
			maxlength : String,
			placeholder : String,
			prompt : String,
			dataType : {
				type:String,
				required : true
			},
			name : String,
			disabled : Boolean, //是否可以操作
			check : Boolean
		},
		data() {
			return {
				info : null ,
				text : "",
				value : "",

				error : false
			}
		},
		watch : {
			value:function( val ){
				if(!this.verification()) return;
				this.$store.commit("setCatch",{
					[this.name] : val
				});
			},
			data( val ){
				if(val){ this.value = val }
			}
		},
		mounted() {

			this.setMsg();
		},
		methods:{
			clear(){
				this.value = "";
			},
			showError(){
				this.error = true;
				this.text = this.info.error;
			},
			clearError(){
				this.error = false;
				this.text = this.info.default;
			},
			verification(callback){
				callback = callback || function(){};
				let self = this ,
					val = $.trim(this.value) ,
					bool = true;
				if(this.check){
					if(!this.prompt){
						if(!this['_'+this.dataType](val)){
							callback(false);
							this.showError();
							return false;
						};
					}else{
						this.prompt.verification.call(this,val , function( b ){
							if(!b){
								callback(false);
								self.showError();
								bool = false;
							}
						})
					}
					if(!bool){
						return false;
					}
				}
				this.clearError();
				callback(true);
				return true;
			},
			setMsg(){
				if(!this.prompt){
					try{
						this.info = ERROR[this.dataType];
					}catch(e){
						console.log("dataType类型出错");
					}
					this.text = this.info.default;
				}else{
					this.info = this.prompt;
					this.text = this.prompt.default;
				}
			}
		}
	}
</script>