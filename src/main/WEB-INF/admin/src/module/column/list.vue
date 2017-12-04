<style scoped>
	@import 'list.css';
</style>
<template>
	<article>
		<div class="form column-list">
			<div class="title">Simple Table</div>
			<div class="content" v-if="list.length">
				<table>
					<tr>
						<th>名称</th>
						<th>排序</th>
						<th>创建时间</th>
						<th>更新时间</th>
						<th>操作</th>
					</tr>
					<tr v-for="item in list" :key="item.id">
						<td>{{item.permission.name}}</td>
						<td>{{item.permission.sort}}</td>
						<td>{{item.permission.createTimeStr|formatTime}}</td>
						<td>{{item.permission.updateTimeStr|formatTime}}</td>
						<td>
							<div class="btn">编辑</div>
							<div class="btn red">删除</div>
						</td>
					</tr>
				</table>
			</div>
		</div>
	</article>
</template>
<script>
	export default {
		data() {
			return {
				list : []
			}
		},
		filters:{
			formatTime(str){
				return T.formatTime(str);
			}
		},
		mounted() {
			const self = this;
			T.ajax({
				url:'/column/list',
				data:{
					page : 1,
					pageSize : 20,
				}
			}).then(data=>{
				self.list = data.data.list;
			});
		}
	}
</script>