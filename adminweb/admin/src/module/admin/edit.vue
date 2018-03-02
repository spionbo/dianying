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
					column = this.column ,
					userId = this.data.userId,
					userName = this.data.userName,
					realName = column.name,
					headImage = column.headImage,
					pwd = column.password;

				require(['../../plug/md5.min.js'], function(md5) {
					self.ajax({
						url : '/user/update',
						type : "POST",
						data : {
							userId : userId,
							userName : userName,
							realName : realName,
							headImage : headImage,
							pwd : md5(md5(pwd))
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