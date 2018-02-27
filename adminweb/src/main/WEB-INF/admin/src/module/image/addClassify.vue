<template>
	<article>
		<div class="form add">
			<div class="title">新增栏目分类</div>
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
	import addForm from "./classfyForm.vue";
	import { mapGetters } from 'vuex';
	export default {
		components: {
			addForm
		},
		computed : {
			...mapGetters(['addColumn'])
		},
		data() {
			return {

			}
		},
		mounted() {
		},
		methods:{
			submit(){
				let [self,ischeck] = [this,true];
				if(!this.$refs.form.verification()) return;
				if(ischeck){
					let column = this.addColumn;
					this.ajax({
						url : '/images/createImagesClassify',
						type : "POST",
						data : {
							classifyName : column.classifyName
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