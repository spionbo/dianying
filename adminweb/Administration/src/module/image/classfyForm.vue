<template>
	<ul>
		<li>
			<div class="label">分类名称<em>*</em></div>
			<form-input
					type="text"
					dataType="chinese"
					name = "classifyName"
					:data = "obj.classifyName"
					:check="true"
					maxlength="20"
					placeholder="请输入栏目分类名称">
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
			...mapGetters(['addColumn'])
		},
		props : {
			data : Object
		},
		data() {
			return {
				list : null,
				obj : {}
			}
		},
		mounted() {
			this.setData();
			this.list = this.menu;
		},
		methods:{
			verification(){
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
						classifyName : data.classifyName
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
