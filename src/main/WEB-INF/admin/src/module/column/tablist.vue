<template>
	<div class="table">
		<div class="tr">
			<div class="th flex">名称</div>
			<div class="th" style="width:120px;text-align:center;">创建时间</div>
			<div class="th" style="width:120px;text-align:center;">更新时间</div>
			<div class="th" style="width:120px;text-align:center;">操作</div>
		</div>
		<div class="list" v-for="item in data" :key="item.column.id">
			<div class="tr no-border" v-if="item.column">
				<div class="td flex">
					<div class="off" v-if="item.columnBeans.length">+</div>
					{{item.column.columnName}}
				</div>
				<div class="td" style="width:120px;text-align:center;">
					{{item.column.createTimeStr | formatTime}}
				</div>
				<div class="td" style="width:120px;text-align:center;">
					{{item.column.updateTimeStr | formatTime}}
				</div>
				<div class="td" style="width:120px;text-align:center;">
					<div class="btn">编辑</div>
					<div class="btn red">修改</div>
				</div>
			</div>
			<div v-if="item.columnBeans.length" class="child">
				<div class="tr">
					<div class="th flex">名称</div>
					<div class="th" style="width:120px;text-align:center;">创建时间</div>
					<div class="th" style="width:120px;text-align:center;">更新时间</div>
					<div class="th" style="width:120px;text-align:center;">操作</div>
				</div>
				<div v-for="obj in item.columnBeans" :key="obj.column.id" class="tr">
					<div class="td flex">
						{{obj.column.columnName}}
					</div>
					<div class="td" style="width:120px;text-align:center;">
						{{obj.column.createTimeStr|formatTime}}
					</div>
					<div class="td" style="width:120px;text-align:center;">
						{{obj.column.updateTimeStr|formatTime}}
					</div>
					<div class="td" style="width:120px;text-align:center;">
						<div class="btn">编辑</div>
						<div class="btn red">删除</div>
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
		}
	}
</script>