<style scoped>
	@import "./style/list.css";
</style>
<template>
	<div class="form horizontal userlist">
		<div class="title">管理员列表</div>
		<div class="content">
			<table>
				<tr>
					<th align="left">真实姓名</th>
					<th>管理员名称</th>
					<th>管理员ID</th>
					<th>创建人</th>
					<th>创建时间</th>
					<th>修改人</th>
					<th>修改时间</th>
					<th align="center">操作</th>
				</tr>
				<tr v-for="item in list" :key="item.id">
					<td>
						<img :src="item.headImage"/>
						{{item.realName}}
					</td>
					<td align="center">{{item.userName}}</td>
					<td align="center">{{item.userId}}</td>
					<td align="center">{{item.createUserName}}</td>
					<td align="center">{{item.createTimeStr}}</td>
					<td align="center">{{item.lastModifyUserName||"-"}}</td>
					<td align="center">{{item.updateTimeStr}}</td>
					<td align="center">
						<div class="btn blue" @click="pemission(item)">修改权限</div>
						<div class="btn" @click="edit(item)">编辑</div>
						<div class="btn red" @click="del(item,$event)">删除</div>
					</td>
				</tr>
			</table>
		</div>
	</div>
</template>
<script>
	import { mapGetters } from 'vuex';
	export default {
		computed : {
			...mapGetters({
				permissions : 'currentMenuPermission'
			})
		},
		data() {
			return {
				list : [],
				page : null
			}
		},
		mounted() {
			const self = this;
			this.ajax({
				url : "/user/userlist"
			}).then(data=>{
				self.list = data.data.list;
				self.page = data.data.page;
			})
		},
		methods:{
			edit( item ){
				require.ensure([],(require)=> {
					this.$requirePop(require('./edit'), {
							props : {
								data : item,
							}
						},
						{
							props: {
								obj: {
									title: "标题",
									close: true,
								}
							}
						}
					);
				});
			},
			del(item,$event){
				this._del({
					item : item,
					$event : $event,
					url : "/user/delete",
					name : `（${item.realName}）的帐号（${item.userName}）`,
					data : {
						userId : item.userId
					}
				});
			},
			pemission( item ){
				let self = this;
				require.ensure([],(require)=> {
					this.$requirePop(require('./pemisstionList'), {
							props : {
								item : item,
								list : self.permissions,
							}
						},
						{
							props: {
								obj: {
									title: "<em class='right'>滑动鼠标可滚动</em>更改\""+item.userName+"\"的权限",
									close: true,
									wrapper : "permission-list-pop",
								}
							}
						});
				});
			}
		}
	}
</script>