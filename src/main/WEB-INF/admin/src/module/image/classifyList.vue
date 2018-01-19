<style scoped>
	.lists{
		display: flex;
		flex-direction: row;
	}
	.flex{
		flex:1;
		text-align: center;
	}
	.td{
		padding:8px;
		line-height:20px;
		vertical-align: middle;
		border-top:1px solid #ddd;
		transition-duration:.5s;
	}
	.th{
		font-size:14px;
		font-weight:600;
		padding:8px;
		line-height: 20px;
	}
</style>
<template>
	<div class="form horizontal userlist">
		<input v-model='searchName' placeholder='请输入搜索内容'>
		<!--<input type="text" v-model='setUpName' placeholder='请输入创建人的名字'>-->
		<!--<input type="text" v-model='setUpName' placeholder='请输入创建人的名字'>-->
		<!--<input type="text" v-model='revisePersonName' placeholder='请输入修改人的名字'>-->
		<div class="title">管理员列表</div>
		<div class="content">
			<div class='lists'>
				<div align="left" class=' th' style='text-align: left;width:60px;'>分类ID</div>
				<div class='flex th'>图片分类名称</div>
				<div class='flex th'>创建人名</div>
				<div class='flex th'>创建时间</div>
				<div class='flex th'>修改人</div>
				<div class='flex th'>修改时间</div>
				<div class='flex th' align="center" width="150">操作</div>
			</div>
			<transition-group  name='classfyList'  v-on:leave="leave"  v-on:enter='enter'   v-on:before-enter="beforeEnter" tag='div'>
				<div v-for="(item,index) in classifyList" :key="item.id" class="lists" :data-index='index'  >
					<div class=' td' style='text-align: left;width:60px;'>
						<img :src="item.headImage"/>
						{{item.id}}
					</div>
					<div align="center" class='flex td'>{{item.classifyName}}</div>
					<div align="center" class='flex td'>{{item.createUserName}}</div>
					<div align="center" class='flex td'>{{item.createTimeStr | formatTime}}</div>
					<div align="center" class='flex td'>{{item.lastModifyUserName||"-"}}</div>
					<div align="center" class='flex td'>{{item.updateTimeStrr | formatTime}}</div>
					<div align="center" class='flex td'>
						<div class="btn flex" @click="edit(item)">编辑</div>
						<div class="btn red flex" @click="del(item,$event)">删除</div>
					</div>
				</div>
			</transition-group>
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
				searchName:'',//添加寻找条件
				isShow:false//
			}
		},
		mounted() {
			this.update();
		},
		watch:{
			searchName:function(){
				if(this.searchName!==""){
					this.isShow=false;
				}else{
					this.isShow=true;
				}
			},
			pageStart:function(){
				this.isShow=true;
			}
		},
		computed:{//添加图片分类搜索动画start11
			classifyList:function(){
				var vm=this;
				return this.list.filter(function(item){
					return item.classifyName.indexOf(vm.searchName)!==-1;
				})
			}//添加图片分类搜索动画end
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
				this._del({
					item : item,
					$event : $event,
					url : "/images/delImagesClassify",
					name : item.classifyName,
					update : this.update,
					data : {
						id : item.id
					}
				});
			},
			beforeEnter: function (el) {
				el.style.opacity = 0
				el.style.height = 0
			},
			enter(el,done){//进入的时候
				var delay=el.dataset.index*100;
				setTimeout(function(){
					Velocity(el,
						{
							opacity:1,
							height:"42px",
							translateZ: "200px",
						},
						{
							complete:done
						}
					)
				},delay)
			},
			leave(el,done){//离开
				if(this.isShow){
					Velocity(el,{opacity:0,height:0},
							{complete:done})
				}else{
					var delay=el.dataset.index*100;
					setTimeout(function(){
						Velocity(el,{opacity:0,height:0},
								{complete:done})
					},delay);
				}
			}
		}
	}
</script>