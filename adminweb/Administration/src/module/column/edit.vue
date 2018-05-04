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
			//const self = this;
		},
		methods:{
			submit(){
				if(!this.$refs.form.verification()) return;

				let self = this,
					column = this.column ,
					select = this.column.selects || [],
					len = select.length,
					permission = this.columnInfo.permission,
					id = permission.id,
					parentId = permission.parentId,
					name = column.name ,
					path = column.path,
					sort = column.sort,
					parentUrl="",
					description = column.description;

				if(len){
					select.forEach((obj,i)=>{
						if(obj=="-1"){
							parentId = null;
							parentUrl = "";
						}else if( i == (len-1)){
							parentUrl = obj.item.url;
							parentId = obj.item.id;
						}
					});
				}
				this.ajax({
					url : '/permission/updateColumn',
					type : "POST",
					data : {
						id : id,
						parentId : parentId,
						name : name,
						url : parentUrl+path,
						sort : sort,
						description : description
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
