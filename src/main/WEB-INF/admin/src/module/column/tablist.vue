<template>
	<div class="table">
		<div class="list" v-for="item in data" :key="item.permission.id">
			<div class="tr">
				<div class="th flex">名称</div>
				<div class="th" style="width:120px;text-align:center;">创建时间</div>
				<div class="th" style="width:120px;text-align:center;">更新时间</div>
				<div class="th" style="width:120px;text-align:center;">操作</div>
			</div>
			<div class="tr" v-if="item.permission">
				<div class="td flex">
					<div class="off" v-if="item.permissionBeans">+</div>
					{{item.permission.name}}
				</div>
				<div class="td" style="width:120px;text-align:center;">
					{{item.permission.createTimeStr | formatTime}}
				</div>
				<div class="td" style="width:120px;text-align:center;">
					{{item.permission.updateTimeStr | formatTime}}
				</div>
				<div class="td" style="width:120px;text-align:center;">
					<div class="btn" @click="edit(item)">编辑</div>
					<div class="btn red" @click="del(item,$event)">删除</div>
				</div>
			</div>
			<div class="tr listTr">
				<div class="th flex">名称</div>
				<div class="th" style="width:120px;text-align:center;">创建时间</div>
				<div class="th" style="width:120px;text-align:center;">更新时间</div>
				<div class="th" style="width:120px;text-align:center;">操作</div>
			</div>
			<div class="list" v-if="item.permissionBeans" v-for="item1 in item.permissionBeans" :key="item1.permission.id">

				<div class="tr" v-if="item1.permission">
					<div class="td flex">
						<div class="off" v-if="item1.permissionBeans">+</div>
						{{item1.permission.name}}
					</div>
					<div class="td" style="width:120px;text-align:center;">
						{{item1.permission.createTimeStr | formatTime}}
					</div>
					<div class="td" style="width:120px;text-align:center;">
						{{item1.permission.updateTimeStr | formatTime}}
					</div>
					<div class="td" style="width:120px;text-align:center;">
						<div class="btn" @click="edit(item1)">编辑</div>
						<div class="btn red" @click="del(item1,$event)">删除</div>
					</div>
				</div>
				<div v-if="item1.permissionBeans" class="child">
					<div class="tr">
						<div class="th flex">名称</div>
						<div class="th" style="width:120px;text-align:center;">创建时间</div>
						<div class="th" style="width:120px;text-align:center;">更新时间</div>
						<div class="th" style="width:120px;text-align:center;">操作</div>
					</div>
					<div v-for="obj in item1.permissionBeans" :key="obj.permission.id" class="tr">
						<div class="td flex">
							<div class="off" v-if="obj.permissionBeans">+</div>
							{{obj.permission.name}}
						</div>
						<div class="td" style="width:120px;text-align:center;">
							{{obj.permission.createTimeStr|formatTime}}
						</div>
						<div class="td" style="width:120px;text-align:center;">
							{{obj.permission.updateTimeStr|formatTime}}
						</div>
						<div class="td" style="width:120px;text-align:center;">
							<div class="btn" @click="edit(obj)">编辑</div>
							<div class="btn red" @click="del(obj,$event)">删除</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</template>
<script>
	export default {
		props:{
			data:Array
		},
		mounted() {
			this.$nextTick(function(){
				let tr = $(".off").parent();
				tr.each(function(){
					if(void 0 == this.isCheck){
						this.isCheck = false;
					}
					let parent = $(this).parent().parent(),
						childList = parent.children(".child"),
						list = parent.children(".list,.listTr").add(childList);
					this.onclick = function(){
						this.isCheck = !this.isCheck;
						if(this.isCheck){
							list.slideUp(200);
						}else{
							list.slideDown(200);
						}
					}
				});
			})
		},
		filters:{
			formatTime(str){
				return T.formatTime(str);
			}
		},
		methods:{
			edit(item){
				this.ajax({
					url:"/permission/permissionColumn",
					type : "get",
					load : true,
					data : {
						columnId : item.permission.id
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
				let [self,tag] = [this,$($event.currentTarget)];
				this.$pop({
					title : "删除",
					close : true,
					content : "确定要删除“"+item.permission.name+"”吗？",
					footer : {
						ok : "确定",
						cancel : "取消",
						okCallback : function(){
							let self = this;
							self.ajax({
								url : "/permission/deleteColumn",
								type : "post",
								load : true,
								data : {
									columnId : item.permission.id
								}
							}).then(data=>{
								self.$closePop();
								self.$tips({
									content: "删除成功!"
								});
								function getParent( tag ){
									if(tag.attr("class").includes("list")){
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