<template>
	<div class="form add edit">
		<add-form ref="form" :data="data"></add-form>
		<div class="submit">
			<div class="btn" @click="submit">确定</div>
			<div class="btn red" @click="clearall">清空</div>
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
			...mapGetters({
				column : 'addColumn'
			})
		},
		props : {
			data : Object,
			columnInfo : Object
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
					column = this.column;

				require(['../../plug/md5.min.js'], function(md5) {
					self.ajax({
						url : '/user/updateInfo',
						type : "POST",
						data : {
							pwd : md5(md5(column.password)),
							pwd1 : md5(md5(column.pwd1)),
							pwd2 : md5(md5(column.pwd2)),
							realName : column.name,
							headImage : column.imageHead,
							userName : column.userName,
						}
					}).then(data=>{
						self.$tips({
							content : "成功！"
						});
						self.$closePop();
					})
				});
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