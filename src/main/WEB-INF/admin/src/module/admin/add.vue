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
			return {}
		},
		mounted() {

		},
		methods:{
			submit(){
				let [self,ischeck] = [this,true];
				if(!this.$refs.form.verification()) return;
				if(ischeck){
					let column = this.column,
						realName = column.name,
						pwd = column.password,
						imageHead = column.imageHead,
						userName = column.userName;

					require(['../../plug/md5.min.js'], function(md5) {
						self.ajax({
							url : '/user/add',
							type : "POST",
							data : {
								userName : userName,
								password : md5(md5(pwd)),
								realName : realName,
								imageHead : imageHead
							},
							load : true,
						}).then(data=>{
							self.$tips({
								content:"添加成功!"
							});
							self.clearall();
						})
					});
				}
			},
			clearall(){
				this.$refs.form.clearall();
			}
		}
	}
</script>