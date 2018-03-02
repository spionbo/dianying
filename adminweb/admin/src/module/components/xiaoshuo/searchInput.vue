<style scoped>
	.edit{
		input.w300{ min-width:300px;}
	}

</style>
<template>
	<div class="txt">
		<div class="edit">
			<input class="w300"
				type="text"
				:placeholder="placeholder"
				v-model="value"
				maxlength="50"
			>
			</input>
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
	};
	export default {
		props: {
			placeholder : String,
			name : String,
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