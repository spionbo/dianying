<style scoped>
	.userlist{
		img{
			width:25px;
			height:25px;
			border-radius:100%;
			vertical-align:middle;
		}
	}
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
					<td align="center">{{item.createTimeStr | formatTime}}</td>
					<td align="center">{{item.lastModifyUserName||"-"}}</td>
					<td align="center">{{item.updateTimeStr}}</td>
					<td align="center">
						<div class="btn" @click="edit(obj)">编辑</div>
						<div class="btn red" @click="del(obj,$event)">删除</div>
					</td>
				</tr>
			</table>
		</div>
	</div>
</template>
<script>
	export default {
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
			edit(){

			},
			del(){

			}
		}
	}
</script>