<style>
	@import './list.css';
</style>
<template>
	<article class="form horizontal column-list permissionList">
		<div class="title">权限列表</div>
		<div class="content">
			<div class="table" v-if="tablist">
				<div class="tr">
					<div class="th flex left">栏目名称</div>
					<div class="th flex center">只读</div>
					<div class="th flex center">写</div>
					<div class="th flex center">更新</div>
					<div class="th flex center">删除</div>
					<div class="th center" style="width:200px">操作</div>
				</div>
				<div class="tr" v-for="item in tablist" v-bind:key="item.id" :class="item.className">
					<div class="td flex left" v-html="item.name"></div>
					<div class="td flex center" v-html="item.permissionVal"></div>
					<div class="td center" style="width:200px">
						<div class="btn" @click="edit(item)">编辑</div>
						<div class="btn red" @click="del(item,$event)">删除</div>
					</div>
				</div>
			</div>
		</div>
	</article>
</template>
<script>
	import { mapGetters } from 'vuex';
	export default {
		computed : {
			...mapGetters({
				list: 'currentMenuPermission'
			})
		},
		data() {
			return {
				tablist : null
			}
		},
		mounted() {
			this.setTabel(this.list);
		},
		watch:{
			list(val){
				this.setTabel(val);
			}
		},
		methods : {
			setColumnEvent(){
				let self = this;
				let add = this.$el.querySelectorAll(".off");
				add.forEach(obj=>{
					obj.clicked = true;
					obj.onclick=function(){
						let selfobj = $(this),
							tr = selfobj.parent().parent() ,
							reg = /\d+$/,
							trClass = tr.attr("class"),
							className = trClass.search(reg);
						obj.clicked = !obj.clicked;
						if(className){
							if(obj.clicked){
								selfobj.find(".fa").removeClass("fa-minus-square").addClass("fa-plus-square");
							}else{
								selfobj.find(".fa").removeClass("fa-plus-square").addClass("fa-minus-square");
							}
							className = parseInt(trClass.match(reg)[0]);
							let childs = self.getChilds(tr,className);
							childs.forEach(ele=>{
								let elem = $(ele) ,
									off = elem.find(".off");
								if(obj.clicked){
									elem.removeClass("hide")
									.find(".fa").removeClass("fa-minus-square").addClass("fa-plus-square");
								}else{
									elem.addClass("hide")
									.find(".fa").removeClass("fa-plus-square").addClass("fa-minus-square");
								}
								if(off[0]) off[0].clicked = obj.clicked;
							});
						}
					}
				})
			},
			getChilds(parent, parentNum){
				let allNext = parent.nextAll() ,
					childs = [];
				for(let i=0;i<allNext.length;i++){
					let trClass = $(allNext[i]).attr("class"),
						parentName = trClass.includes("open"+parentNum),
						childName = trClass.includes("open"+(parentNum+1));
					if(parentName) break;
					childs.push(allNext[i]);
				}
				return childs;
			},
			setTabel(list){
				const self = this;
				if(!list || !list.length) return;
				let copyList = Array.from(list) ,
					newArr = [];
				function setChildName( copylist , val , index  ){ //栏目名称 按树状显示
					copylist.forEach(obj=>{
						let isChild = obj.permissionBeans ,
							len = isChild?isChild.length:-1;

						let on =`<span class="off" data-length="${len}" v-if="item.isChild">
								<i class="fa fa-plus-square"></i>
							</span>`,
							parent = val.replace(/\|\-/g,on);
						newArr.push({
							sort:obj.permission.sort,
							id:obj.permission.id,
							name:(isChild?parent:val)+obj.permission.name,
							parentId:obj.permission.parentId,
							permissionKey:obj.permission.permissionKey,
							permissionVal:obj.permission.permissionVal,
							isChild : isChild,
							className : "open"+index
						});
						if(isChild){
							setChildName(obj.permissionBeans,"　　　"+val,(index+1));
						}
					});
					return copylist;
				}
				setChildName(copyList,"|- ",1);
				newArr.forEach(obj=>{
					if(obj.permissionVal){
						obj.permissionVal = self.permissionName(obj.permissionVal);
					}
				});
				self.tablist = newArr;
				self.$nextTick(()=>{
					self.setColumnEvent();
				});
			},
			permissionName(val){
				if(!val) return "无权限";
				let item = {read:"读",update:"更",write:"写","delete":"删"},
					vals = val.split(",") ,
					elem = "";
				vals.forEach(obj=>{
					elem += `<span class="${obj}">${item[obj]}</span>`;
				});
				return elem;
			}
		}
	}
</script>