<style scoped>
	@import "../column/list.css";
</style>
<style>
	@import "./style/permissionList.css";
</style>
<template>
	<article class="form horizontal column-list" v-if="list.length">
		<div class="table" v-if="tablist">
			<div class="tr">
				<div class="th center w80">ID</div>
				<div class="th left flex">栏目名称</div>
				<div class="th center w80">父类ID</div>
				<div class="th center w300">拥有权限</div>
				<div class="th center w100">是否展示</div>
			</div>
			<article class="tbody">
				<div class="tr" v-for="item in tablist" v-bind:key="item.id" :class="item.className">
					<div class="td center w80">{{item.id}}</div>
					<div class="td left flex" v-html="item.name"></div>
					<div class="td center w80">{{item.parentId||"-"}}</div>
					<div class="td center w300">
						<checkbox v-for="obj in item.permissionVal"
						          v-bind:key="obj.id"
						          :checked="obj.checked"
						          :data="obj"></checkbox>
					</div>
					<div class="td center w100">
						<checkbox :checked="item.delTag" :data="{right:'是',error:'否'}"></checkbox>
					</div>
				</div>
			</article>
		</div>
		<div class="submit">
			<div class="btn" @click="submit">确定修改</div>
			<div class="explort">
				警告：权限开通后，管理员将直接可以更改删除相关栏目及内容，删除后不可再找回。
			</div>
		</div>
	</article>
</template>
<script>
	import checkbox from '../components/checkbox.vue';
	export default {
		components:{
			checkbox
		},
		props:{
			list : Array,
			item : Object
		},
		data() {
			return {
				tablist : null
			}
		},
		mounted() {
			this.setTabel(this.list);
		},
		methods:{
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
			permissionName(val){
				let item = {read:"读",update:"更",write:"写","delete":"删"},
					vals ,
					arr = [];

				if((val && (vals=val.split(",")))){
					vals.forEach(_val=>{
						arr.push({
							right:item[_val],
							error:item[_val],
							checked : true
						})
					});
				}else{
					for(let val in item){
						arr.push({
							right:item[val],
							error:item[val],
							checked : false
						})
					}
				}
				return arr;
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
							delTag:obj.permission.delTag,
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
					obj.permissionVal = self.permissionName(obj.permissionVal);
				});
				self.tablist = newArr;
				self.$nextTick(()=>{
					self.setColumnEvent();
				});
			},
			submit(){

			}
		}
	}
</script>