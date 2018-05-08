<style>
</style>
<template>
	<article>
		<div class="form add">
			<div class="title">新增分类</div>
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
	import addForm from "./form/classfyForm.vue";
	import { mapGetters } from 'vuex';
	export default {
		computed : {
			...mapGetters(['addColumn'])
		},
		components: {
			addForm
		},
		data() {
			return {}
		},
		mounted() {

		},
		methods:{
			submit(){
				const self = this;
				if(!this.$refs.form.verification()) return;
				this.ajax({
					url:'/xiaoshuo/addClassify',
					type:'post',
					data : {
						name : this.addColumn.classifyName
					}
				}).then(data=>{
					this.$tips({
						content:"添加成功!"
					});
					self.clearall();
				})
			},
			clearall(){
				this.$refs.form.clearall();
			}
		}
	}
</script>
