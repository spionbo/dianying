<style>
</style>
<template>
	<div class="txt">
		<div class="edit">
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
		<div class="cnt" :class="{cur:error}">
			{{text}}
		</div>
	</div>
</template>
<script>
	import myMixin from "../../common/mixin";
	const ERROR = {
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
        }
	};
	export default {
		mixins: [myMixin],
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
				})
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