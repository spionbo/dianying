<template>
	<ul>
		<li>
			<div class="label">基础URL<em>*</em></div>
			<form-input
					type="text"
					dataType="url"
					name = "baseUrl"
					:data = "obj.baseUrl"
					:check="true"
					maxlength="11"
					placeholder="请输入路经名称">
			</form-input>
		</li>
		<li>
			<div class="label">基础路径<em>*</em></div>
			<form-input
					type="text"
					dataType="path"
					name = "basePath"
					:data = "obj.basePath"
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
				obj : {}
			}
		},
		mounted() {
			this.setData();
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