<template>
	<div>
		<div class="content">
			<table>
				<tr>
					<th width="50">分类ID</th>
					<th align="left">标题</th>
					<th>作者</th>
					<th width="50">状态</th>
					<th width="50">排序</th>
					<th width="90">总数</th>
					<th>修改ID</th>
					<th>修改时间</th>
					<th align="center" width="250">操作</th>
				</tr>
				<tr v-for="item in list" :key="item.id" class="list">
					<td align="center">{{item.parentId}}</td>
					<td align="left"><span class="link" @click="chapterlist(item)">{{item.title}}</span></td>
					<td align="center">{{item.author}}</td>
					<td align="center">
						<span class="tag" :class="{green:item.status==1}">
							{{item.status==1?"更新":"完结"}}
						</span>
					</td>
					<td align="center">{{item.sort}}</td>
					<td align="center">{{item.count}}</td>
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
				list : [],
				page : null,
				pageStart : 1,
				pageSize : 15,
			}
		},
		mounted() {
			this.update();
		},
		methods:{
			update(){
				const self = this;
				this.ajax({
					url : "/xiaoshuo/list",
					data : {
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
			chapterlist( item ){
				this.$store.commit('setAbout',item);
				router.push('chapterList');
			}
		}
	}
</script>