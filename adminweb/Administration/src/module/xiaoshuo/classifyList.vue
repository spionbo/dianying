<style>
</style>
<template>
	<div class="form horizontal userlist">
		<div class="title">分类列表</div>
		<div class="content">
			<table>
				<tr>
					<th align="left">分类ID</th>
					<th>分类名称</th>
					<th>创建人名</th>
					<th>创建时间</th>
					<th>修改人</th>
					<th>修改时间</th>
					<th align="center" width="150">操作</th>
				</tr>
				<tr v-for="item in list" :key="item.id" class="list">
					<td>
						<img :src="item.headImage"/>
						{{item.id}}
					</td>
					<td align="center">{{item.classifyName}}</td>
					<td align="center">{{item.createUserName}}</td>
					<td align="center">{{item.createTimeStr | formatTime}}</td>
					<td align="center">{{item.lastModifyUserName||"-"}}</td>
					<td align="center">{{item.updateTimeStrr | formatTime}}</td>
					<td align="center">
						<div class="btn" @click="edit(item)">编辑</div>
						<div class="btn red" @click="del(item,$event)">删除</div>
					</td>
				</tr>
			</table>
		</div>
	</div>
</template>
<script>
    import {CLASSIFY_LIST} from "../../contant/URLS/XIAOSHUO";
	export default {
		data() {
			return {
				list : []
			}
		},
		mounted() {
			this.update();
		},
		methods:{
			update(){
				const self = this;
				this.ajax({
					url : CLASSIFY_LIST,
				}).then(data=>{
					self.list = data.data.list;
				})
			},
			edit(item){
				let self = this;
				require.ensure([],(require)=> {
					this.$requirePop(require('./editClassify'), {
							props : {
								data : item,
								update : self.update
							}
						},
						{
							props: {
								obj: {
									title: "更改分类名称",
									close: true,
								}
							}
						});
				});
			},
			del(item,$event){
				/*this._del({
					item : item,
					$event : $event,
					url : "/images/delImagesClassify",
					name : item.classifyName,
					update : this.update,
					data : {
						id : item.id
					}
				});*/
			}
		}
	}
</script>
