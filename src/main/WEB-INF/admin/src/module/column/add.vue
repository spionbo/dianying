<style>
</style>
<template>
	<article>
		<div class="form add">
			<div class="title">新增栏目</div>
			<div class="content">
				<add-form ref="form"></add-form>
				<div class="submit">
					<div class="btn" @click="submit">确定</div>
					<div class="btn red">清空</div>
				</div>
			</div>
		</div>
	</article>
</template>
<script>
	import addForm from "./addForm.vue";
	import { mapGetters } from 'vuex';
	export default {
		components: {
			addForm
		},
		computed : {
			...mapGetters({
				column : 'addColumn'
			})
		},
		data() {
			return {
				list : null,

				sort : 1,
				description : ""
			}
		},
		mounted() {
			const self = this;
			T.ajax({
				url:'/permission/currentMenuPermission',
			}).then(data=>{
				self.list = data.data;
			});
		},
		methods:{
			submit(){
				let [self,ischeck] = [this,true];
				if(!this.$refs.form.verification()) return;
				if(ischeck){
					let column = this.column,
						select = this.column.column,
						obj = {
							name : column.name,
							parentId : select.id,
							parentUrl : select.url,
							url : column.path,
							sort : this.sort,
							description : this.description
						};
					this.$pops.loadding();
					T.ajax({
						url : '/permission/add',
						type : "POST",
						data : obj
					}).then(data=>{
						self.$pops.removeLoadding();
						this.$pop({
							title : "成功",
							close : true,
							content : "添加成功！"
						});
					})
				}
			}
		}
	}
</script>