<template>
	<div class="table" v-if="tablist">
		<div class="tr">
			<div class="th center" style="width:80px;">排序</div>
			<div class="th center" style="width:150px;">ID</div>
			<div class="th flex left">栏目名称</div>
			<div class="th flex center">父类ID</div>
			<div class="th center" style="width:200px">操作</div>
		</div>
		<div class="tr" v-for="item in tablist" v-bind:key="item.id" :class="item.className">
			<div class="td center" style="width:80px;">{{item.sort||"-"}}</div>
			<div class="td center" style="width:150px;">{{item.id}}</div>
			<div class="td flex left" v-html="item.name"></div>
			<div class="td flex center">{{item.parentId||"-"}}</div>
			<div class="td center" style="width:200px">
				<div class="btn" @click="edit(item)">编辑</div>
				<div class="btn red" @click="del(item,$event)">删除</div>
			</div>
		</div>
	</div>
</template>
<script>
	import mixin from "../../common/mixin/_permission";
	export default {
		mixins : [mixin],
		props:{
			data:Array
		},
		data(){
			return {
				tablist : null
			}
		},
		watch:{
			data(_data){
				this._setColumnToTree(_data);
			}
		},
		mounted() {
			this._setColumnToTree(this.data);
		},
		filters:{
			formatTime(str){
				return T.formatTime(str);
			}
		},
		methods:{
			edit(item){
				console.log(item)
				this.ajax({
					url:"/permission/permissionColumn",
					type : "get",
					load : true,
					data : {
						columnId : item.id
					}
				}).then(data=>{
					require.ensure([],(require)=> {
						this.$requirePop(require('./edit'), {
							props : {
								data : data.data,
								columnInfo : item
							}
						},
						{
							props: {
								obj: {
									title: "标题",
									close: true,
								}
							}
						});
					});
				});

			},
			del(item,$event){
				this._del({
					item : item,
					$event : $event,
					url : "/permission/deleteColumn",
					name : item.name.replace(/\　+\|\-/g,""),
					data : {
						columnId : item.id
					}
				});
			}
		}
	}
</script>