<style>
</style>
<template>
	<div class="form add edit">
		<add-form ref="form" :data="data"></add-form>
		<div class="submit">
			<div class="btn" @click="submit">确定修改</div>
		</div>
	</div>
</template>
<script>
	import addForm from "./addForm.vue";
	import { mapGetters } from 'vuex';
	export default {
		components : {
			addForm
		},
		computed : {
			...mapGetters([
				'addColumn'
			])
		},
		props : {
			data : Object,
			columnInfo : Object ,
			update : Function
		},
		data() {
			return {
			}
		},
		mounted() {
			const self = this;
		},
		methods:{
			submit(){
				if(!this.$refs.form.verification()) return;
				let self = this,
					addColumn = this.addColumn;
				this.ajax({
					url : '/images/updateImagesClassify',
					type : "POST",
					data : {
						id : self.data.id,
						classifyName : addColumn.name,
					}
				}).then(data=>{
					this.$tips({
						content : "成功！"
					});
					self.$closePop();
					self.update();
				})
			},
			$closePop(){
				this.$parent.close();
			}
		}

	}
</script>