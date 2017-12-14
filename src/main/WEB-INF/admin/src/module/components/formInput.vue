<style>
</style>
<template>
	<div class="txt">
		<div class="edit">
			<input
					:type="type"
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
		}
	};
	export default {
		mixins: [myMixin],
		props: {
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
				value : null,

				error : false
			}
		},
		watch : {
			value:function( val ){
				if(!this.verification()) return;
				this.$store.commit("setCatch",{
					[this.name] : val
				})
			}
		},
		mounted() {
			this.setMsg();
		},
		methods:{
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
						callback(false)
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
					this.info = ERROR[this.dataType];
				}catch(e){
					console.log("dataType类型出错");
				}

				this.text = this.prompt || this.info.default;
			}
		}
	}
</script>