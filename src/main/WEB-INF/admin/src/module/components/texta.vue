<style>
</style>
<template>
	<div class="txt">
		<div class="textarea">
			<textarea
				class="text"
				:placeholder="placeholder"
				:rows="rows"
				:maxlength="maxlength"
				v-model="value"
			>
			</textarea>
		</div>
		<div class="cnt" :class="{cur:error}">
			{{text}}
		</div>
	</div>
</template>
<script>
	import myMixin from "../../common/mixin";
	const ERROR = {
		default : {
			default : "请输入内容",
			error : "请输入内容"
		}
	};
	export default {
		mixins: [myMixin],
		props: {
			data : String,
			placeholder : String,
			maxlength : Number,
			prompt : String,
			name : String,
			rows : {
				type:Number,
				default:'5'
			},
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
				let val = $.trim(this.value);
				if(this.check){
					if(!this['_'+this.dataType](val)){
						callback(false);
						this.showError();
						return false;
					};
				}
				this.clearError();
				callback(true);
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