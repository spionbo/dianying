<style>
</style>
<template>
	<article>
		<div class="form add">
			<div class="title">新增图片分类</div>
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
				addColumn : 'addColumn'
			})
		},
		data() {
			return {}
		},
		mounted() {

		},
		methods:{
            submit(){
            	let self = this;
                if(!this.$refs.form.verification()) return;
                this.ajax({
	                url : "/images/createImagesClassify",
	                data : {
		                classifyName : this.addColumn.name,
	                },
	                type : "post"
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