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
    import mixinList from '../../common/_mixinList';
    export default {
        mixins: [mixinList],
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
						this.$requirePop(require('./editClassify'), {
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
			    this._del(item,$event,"/images/delImagesClassify",item.classifyName);
			}
		}
	}
</script>