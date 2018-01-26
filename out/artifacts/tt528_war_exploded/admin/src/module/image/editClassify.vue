<template>
	<div class="form add edit">
		<add-form ref="form" :data="data"></add-form>
		<div class="submit">
			<div class="btn" @click="submit">确定</div>
		</div>
	</div>
</template>
<script>
	import addForm from "./classfyForm.vue";
	import { mapGetters } from 'vuex';
	export default {
		components : {
			addForm
		},
		computed : {
			...mapGetters({
				column : 'addColumn'
			})
		},
		props : {
			data : Object,
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
					column = this.column,
					id = this.data.id,
					classifyName = column.classifyName;

				this.ajax({
					url : '/images/updateImagesClassify',
					type : "POST",
					data : {
						id : id,
						classifyName : classifyName,
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