<template>
	<ul>
		<li>
			<div class="label">图片分类<em>*</em></div>
			<form-input
					type="text"
					:prompt="prompt"
					name = "imgClass"
					:data = "obj.imgClass"
					:check="true"
					maxlength="11"
					placeholder="请输入路经名称">
			</form-input>
		</li>
	</ul>
</template>
<script>
	import formInput from "../components/formInput";
	import { mapGetters } from 'vuex';
	export default {
		components: {
			formInput
		},
		computed : {
			...mapGetters({
				column : 'addColumn'
			})
		},
		props : {
			data : Object
		},
		data() {
			return {
				obj : {},
				prompt : {
					default : "请输入分类名称",
					error : "分类名称为中文",
					verification( val , callback ){
						callback(this._chinese(val));
					}
				}
			}
		},
		mounted() {
		},
		methods:{
			verification(){
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
			setData(){
				let [
					self,
					data
				] = [
					this,
					this.data
				];
				if(data){
					self.obj = {
						imgClass : data.imgClass
					};
				}
			},
			clearall(){
				this.obj = {};
				this.$children.forEach(obj=>{
					obj.clear();
				})
			}
		}
	}
</script>