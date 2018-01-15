<template>
	<div class="table" v-if="tablist">
		<div class="tr">
			<div class="th center" style="width:80px;">排序</div>
			<div class="th center" style="width:150px;">ID</div>
			<div class="th flex left">栏目名称</div>
			<div class="th flex center">父类ID</div>
			<div class="th flex center">权限名称</div>
			<div class="th flex center">拥有权限</div>
			<div class="th center" style="width:200px">操作</div>
		</div>
		<div class="tr" v-for="item in tablist" v-bind:key="item.id" :class="item.className">
			<div class="td center" style="width:80px;">{{item.sort||"-"}}</div>
			<div class="td center" style="width:150px;">{{item.id}}</div>
			<div class="td flex left" v-html="item.name"></div>
			<div class="td flex center">{{item.parentId||"-"}}</div>
			<div class="td flex center">{{item.permissionKey}}</div>
			<div class="td flex center" v-html="item.permissionVal"></div>
			<div class="td center" style="width:200px">
				<div class="btn" @click="edit(item)">编辑</div>
				<div class="btn red" @click="del(item,$event)">删除</div>
			</div>
		</div>
	</div>
</template>
<script>
	export default {
		props:{
			data:Array
		},
		data(){
			return {
				tablist : null
			}
		},
		watch:{
			data(val){
				this.setTabel(val);
			}
		},
		mounted() {
			let self = this;
			this.setTabel(this.data);
			this.$nextTick(()=>{
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
			})
		},
		filters:{
			formatTime(str){
				return T.formatTime(str);
			}
		},
		methods:{
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
			},
			edit(item){
				console.log(item)
				this.ajax({
					url:"/permission/permissionColumn",
					type : "get",
					load : true,
					data : {
						columnId : item.id
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
				this._del({
					item : item,
					$event : $event,
					url : "/permission/deleteColumn",
					name : item.name.replace(/\　+\|\-/g,""),
					data : {
						columnId : item.id
					}
				});
			}
		}
	}
</script>