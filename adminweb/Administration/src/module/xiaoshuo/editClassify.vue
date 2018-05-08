<template>
	<div class="form add edit">
		<add-form ref="form" :data="data"></add-form>
		<div class="submit">
			<div class="btn" @click="submit">确定</div>
		</div>
	</div>
</template>
<script>
	import addForm from "./form/classfyForm.vue";
	import { mapGetters } from 'vuex';
	import {UPDATE_CLASSIFY} from "../../contant/URLS/XIAOSHUO";
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
			data : Object
		},
		data() {
			return {
			}
		},
		mounted() {
			//const self = this;
		},
		methods:{
			submit(){
				if(!this.$refs.form.verification()) return;
				let self = this,
					id = this.data.id,
					classifyName = this.column.classifyName;
				this.ajax({
					url : UPDATE_CLASSIFY,
					type : "POST",
					data : {
						id : id,
						classifyName : classifyName
					}
				}).then(data=>{
					this.$tips({
						content : "成功！"
					});
					self.$closePop();
				})
			},
			$closePop(){
				this.$parent.close();
			},
			clearall(){
				this.$refs.form.clearall();
			}
		}

	}
</script>
