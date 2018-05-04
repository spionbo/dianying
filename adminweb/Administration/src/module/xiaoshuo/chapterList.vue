<style>
</style>
<template>
	<div class="form horizontal userlist">
		<div class="title" v-if="about">{{about.title}}</div>
		<div class="content">
			<table>
				<tr>
					<th width="50">ID</th>
					<th align="left">标题</th>
					<th width="100">排序/章节</th>
					<th>修改ID</th>
					<th>修改时间</th>
					<th align="center" width="250">操作</th>
				</tr>
				<tr v-for="item in list" :key="item.id" class="list">
					<td align="center">{{item.id}}</td>
					<td align="left">{{item.title}}</td>
					<td align="center">{{item.num}}</td>
					<td align="center">{{item.lastModifyUserId || "-"}}</td>
					<td align="center">{{item.updateTimeStrr | formatTime}}</td>
					<td align="center">
						<div class="btn" @click="edit(item)">编辑</div>
						<div class="btn red" @click="del(item,$event)">删除</div>
					</td>
				</tr>
			</table>
		</div>
		<page :page="page" @$change="updatePage"></page>
	</div>
</template>
<script>
	export default {
		data() {
			return {
				about : null,
				list : [],
				page : null,
				pageStart : 1,
				pageSize : 15,
			}
		},
		mounted() {
			try{
				this.about = this.$store.state.xiaoshuo.about;
			}catch(e){
				router.go(-1);
			}
			if(!this.about) return;
			this.update();

		},
		methods : {
			update(){
				const self = this;
				this.ajax({
					url : "/xiaoshuo/chapterList",
					data : {
						id : self.about.id,
						page : self.pageStart,
						pageSize : self.pageSize
					}
				}).then(data=>{
					self.list = data.data.list;
					self.page = data.data.page;
				});
			},
			updatePage( index ){
				this.pageStart = index;
				this.update();
			},
		}
	}
</script>