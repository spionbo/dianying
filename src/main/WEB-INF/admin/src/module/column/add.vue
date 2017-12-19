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
			this.ajax({
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
						select = this.column.selects,
						parentId,
						parentUrl = "";
					if(select.length){
						parentId = select.pop().item.id;
						column.selects.forEach(obj=>{
							parentUrl += obj.item.url
						});
					}
					this.ajax({
						url : '/permission/addColumn',
						type : "POST",
						data : {
							name : column.name,
							parentId : parentId,
							sort : this.sort,
							url : parentUrl+obj.url,
							description : this.description
						},
						load : true,
					}).then(data=>{
						this.$tips({
							content:"添加成功!"
						});
						self.clearall();
					})
				}
			},
			clearall(){
				this.$refs.form.clearall();
			}
		}
	}
</script>