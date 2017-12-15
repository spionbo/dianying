<template>
	<div class="table">
		<div class="tr">
			<div class="th flex">名称</div>
			<div class="th" style="width:120px;text-align:center;">创建时间</div>
			<div class="th" style="width:120px;text-align:center;">更新时间</div>
			<div class="th" style="width:120px;text-align:center;">操作</div>
		</div>
		<div class="list" v-for="item in data" :key="item.permission.id">
			<div class="tr no-border" v-if="item.permission">
				<div class="td flex">
					<div class="off" v-if="item.permissionBeans.length">+</div>
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
					<div class="btn red" v-if="!item.permissionBeans" @click="del(item)">删除</div>
				</div>
			</div>
			<div v-if="item.permissionBeans.length" class="child">
				<div class="tr">
					<div class="th flex">名称</div>
					<div class="th" style="width:120px;text-align:center;">创建时间</div>
					<div class="th" style="width:120px;text-align:center;">更新时间</div>
					<div class="th" style="width:120px;text-align:center;">操作</div>
				</div>
				<div v-for="obj in item.permissionBeans" :key="obj.permission.id" class="tr">
					<div class="td flex">
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
						<div class="btn red" v-if="!obj.permissionBeans" @click="del(obj)">删除</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</template>
<script>
	import dd from "./add";
	export default {
		props:{
			data:Array
		},
		mounted() {
			this.$nextTick(function(){
				let list = $(".list");
				list.each(function(){
					$(this).find('.off').click(function(){
						let tr = $(this).parent().parent(),
							child = tr.next();
						if(void 0 == this.checked){
							this.checked = false;
						};
						this.checked = !this.checked;
						if(child.length){
							if(this.checked){
								tr.removeClass("no-border");
								$(this).html("-");
								child.show();
							}else{
								tr.addClass("no-border");
								$(this).html("+");
								child.hide();
							}
						}
					})
				});
			});
		},
		filters:{
			formatTime(str){
				return T.formatTime(str);
			}
		},
		methods:{
			edit(item){

				T.ajax({
					url:"/permission/permissionColumn",
					type : "get",
					data : {
						columnId : item.permission.id
					}
				}).then(data=>{
					require.ensure([],(require)=> {
						this.$requirePop(require('./edit'), {
							props : {
								data : data.data
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
			del(item){

			}
		}
	}
</script>