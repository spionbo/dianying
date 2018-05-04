export default {
	methods:{
		_setColumnToTree( list ){ //栏目设置成树状
			const self = this;
			if(!list || !list.length) return;
			let copyList = Array.from(list) ,
				newArr = [];
			function setChildName( copylist , val , index  ){ //栏目名称 按树状显示
				copylist.forEach(obj=>{
					let isChild = obj.permissionBeans ,
						len = isChild?isChild.length:-1;

					let on =`<span class="off" data-length="${len}" v-if="item.isChild">
								<i class="fa columnOnAndOf fa-minus-square"></i>
							</span>`,
						parent = val.replace(/\|\-/g,on);
					newArr.push({
						sort:obj.permission.sort,
						id:obj.permission.id,
						powerKey:obj.permission.powerKey,
						name:(isChild?parent:val)+obj.permission.name,
						parentId:obj.permission.parentId,
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
				obj.power = self._permissionName(obj.permissionVal);
			});
			self.tablist = newArr;
			self.$nextTick(()=>{
				self._columnOnAndOf();
			});
		},
		_getChilds(parent, parentNum){ //获取子类，
			let allNext = parent.nextAll() ,
				childs = [];
			for(let i=0; i<allNext.length; i++){
				let trClass = $(allNext[i]).attr("class"),
					parentName = trClass.includes("open"+parentNum);
					//childName = trClass.includes("open"+(parentNum+1));
				if(parentName) break;
				childs.push(allNext[i]);
			}
			return childs;
		},
		_permissionName(val){
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
						key : val,
						checked : false
					})
				}
			}
			return arr;
		},
		_columnOnAndOf(){ //展开-收缩栏目
			let self = this,
				on = "fa-plus-square",
				off = "fa-minus-square",
				fa = ".columnOnAndOf",
				add = this.$el.querySelectorAll(".off");
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
							selfobj.children(fa).removeClass(on).addClass(off);
						}else{
							selfobj.children(fa).removeClass(off).addClass(on);
						}
						className = parseInt(trClass.match(reg)[0]);
						let childs = self._getChilds(tr,className);
						childs.forEach(ele=>{
							let elem = $(ele);
								//childOff = elem.children(".off");
							if(obj.clicked){
								elem.removeClass("hide")
								.children(fa).removeClass(on).addClass(off);
							}else{
								elem.addClass("hide")
								.children(fa).removeClass(off).addClass(on);
							}
						});
					}
				}
			})
		}
	}
}
