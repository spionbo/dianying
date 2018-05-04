<style>
</style>
<template>
	<div class="txt">
		<div class="edit">
			<input
				type="number"
				:min="min"
				:max="max"
				v-model="value"
			/>
		</div>
		<div class="cnt" :class="{cur:error}">
			{{text}}
		</div>
	</div>
</template>
<script>
	const ERROR = {
		default : {
			default : "请选择数字",
			error : "请选择数字"
		}
	};
	export default {
		props: {
			data : String,
			min : Number,
			max : Number,
			prompt : String,
			name : String,
			check : Boolean
		},
		data() {
			return {
				info : null ,
				text : "",
				value : 1,
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
			verification(_obj){
			    let obj = {
			        callback : function(){}
                }
                Object.assign(obj,_obj);
				let val = $.trim(this.value);
				if(this.check){
					if(!this['_'+this.dataType](val)){
						obj.callback(false);
						this.showError();
						return false;
					};
				}
				this.clearError();
				obj.callback(true);
				return true;
			},
			setMsg(){
				try{
					this.info = ERROR[this.dataType||"default"];
				}catch(e){
					console.log("dataType类型出错");
				}

				this.text = this.prompt || this.info.default;
			}
		}
	}
</script>
