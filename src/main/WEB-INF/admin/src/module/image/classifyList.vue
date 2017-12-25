<style>
</style>
<template>
	<div class="form horizontal userlist">
		<div class="title">管理员列表</div>
		<div class="content">
			<table>
				<tr>
					<th align="left">分类ID</th>
					<th>图片分类名称</th>
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
				pageSize : 15
			}
		},
		mounted() {
			this.update();
		},
		methods:{
			update(){
				const self = this;
				this.ajax({
					url : "/images/imagesclassifylist",
					data : {
						page : self.pageStart,
						pageSize : self.pageSize
					}
				}).then(data=>{
					self.list = data.data.list;
					self.page = data.data.page;
				})
			},
			updatePage( index ){
				this.pageStart = index;
				this.update();
			},
			edit(item){
				let self = this;
				this.ajax({
					url:"/images/imagesclassify",
					type : "get",
					load : true,
					data : {
						id : item.id
					}
				}).then(data=>{
					require.ensure([],(require)=> {
						this.$requirePop(require('./edit'), {
								props : {
									data : data.data,
									columnInfo : item,
									update : self.update
								}
							},
							{
								props: {
									obj: {
										title: item.classifyName,
										close: true,
									}
								}
							});
					});
				});
			},
			del(item,$event){
				let [self,tag] = [this,$($event.currentTarget)];
				this.$pop({
					title : "删除",
					close : true,
					content : "确定要删除“"+item.classifyName+"”吗？",
					footer : {
						ok : "确定",
						cancel : "取消",
						okCallback : function(){
							let self = this;
							self.ajax({
								url : "/images/delImagesClassify",
								type : "post",
								load : true,
								data : {
									id : item.id
								}
							}).then(data=>{
								self.$closePop();
								self.$tips({
									content: "删除成功!"
								});
								function getParent( tag ){
									let cls = tag.attr("class");
									if(cls && cls.includes("list")){
										tag.remove();
									}else{
										getParent(tag.parent());
									}
								}
								getParent(tag);
							});
						}
					}
				});

			}
		}
	}
</script>