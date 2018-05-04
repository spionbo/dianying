<style>
</style>
<template>
	<div class="txt">
		<div class="select">
			<choose ref="select1"
			        index="1"
					:placeholder="placeholder"
					v-on:$change="change"
			        :list = "list"
			        :select="selected1"
			></choose>
			<choose v-if="list2.length" ref="select2"
			        index="2"
					:placeholder="placeholder"
					v-on:$change="change"
					:list = "list2"
					:select="selected2"
			></choose>
			<choose v-if="list3.length" ref="select3"
			        index="3"
					:placeholder="placeholder"
					v-on:$change="change"
					:list = "list3"
					:select="selected3"
			></choose>
		</div>
		<div class="cnt" :class="{cur:error}">
			请选择分类
		</div>
	</div>
</template>
<script>
	import { mapGetters } from 'vuex';
	import choose from "./select.vue";
	export default {
		components : {
			choose
		},
		props:{
			columnId : Number ,
			arr : Array,
			columnName : String, //数组名称转换
			columnObjName : String, //数组对像名称转换
			columnListName : String,//数组列表名称转换
			name : String,

			placeholder : String,
			check : Array,//目前最多3个
		},
		computed : {
			...mapGetters({
				column : 'addColumn'
			})
		},
		data() {
			return {
				list : [],
				list2 : [],
				list3 : [],

				selected1 : -1,
				selected2 : -1,
				selected3 : -1,
				selects : [], //0为第1个选择匡，1为第二个，。。。

				error : false
			}
		},
		watch:{
			arr(val){
				this.list = this.setList(null,this.arr);
			},
			columnId(val){
				if( void 0 == val) return;
				let obj = this.setSelect(this.list,this.list,val) ,
					arr = this.getIndex(obj);
				this.selects = arr;
				this.setSelected(0,arr);
			}
		},
		mounted() {

		},
		methods : {
			clear(){
				this.$children.forEach(obj=>{
					obj.clear();
				})
			},
			showError(){
				this.error = true;
			},
			clearError(){
				this.error = false;
			},
			getIndex(obj){ //获取有多少个 select arr.lenth [父类，子类1，子类2，子类3]
				if(!obj) return obj;
				let arr = [obj];
				if(obj.parent){
					arr.unshift(obj.parent);
					if(obj.parent.parent){
						arr.unshift(obj.parent.parent);
						if(obj.parent.parent.parent){
							arr.unshift(obj.parent.parent.parent);
						}
					}
				}
				if(arr[0].item.parentId){
					this.list.forEach(_obj=>{ //最外层的没有parent
						if(_obj.item.id == arr[0].item.parentId){
							arr.unshift(_obj);
						}
					});
				}

				return arr;
			},
			setSelected(i,arr){ //设置select选择项
				if(!arr || !arr[i+1]) return;
				this['selected'+(i+1)] = arr[i];
			},
			setSelect( parent , list , id ){ //查找当前选择的栏目
				let obj;
				for(let i=0; i<list.length; i++){
					if(list[i].item.id == id){
						obj = list[i];
						break;
					}
				}
				if(!obj){
					for(let j=0; j<list.length; j++){
						if(list[j].list){
							let bool = this.setSelect(list[j],list[j].list,id);
							if(bool){
								obj = bool;
								break;
							}
						}
					}
				}
				return obj;
			},
			change( obj , index ){
				index = parseInt(index);
				//把后面的select设置为-1
				if(this['selected'+(index+1)]){
					this['selected'+(index+1)] = -1;
				}
				const self = this ,
					arr = this.column[this.name] || [];
				arr[(index-1)] = obj; //index 从0 开始，所以要-1
				self.$store.commit("setCatch",{
					[this.name] : arr
				});
				self['list'+(index+1)] = []; //list2 才是第二个，选择第一个，要加1才到第二个
				if(obj.list){
					self['list'+(index+1)] = obj.list;
					this.$nextTick(()=>{ //更新第一个select时，第二个才会出来，所以需要监听
						this.setSelected(index,this.selects);
					});
				}
				this.verification();
			},
			verification(_callback){
                _callback = _callback || function(){};
				let selcts = this.column[this.name] ,
					bool = true;
				this.check.forEach((val,i)=>{
					if(val && ( !selcts || (selcts[i] && selcts[i] == -1))){
						bool = false;
						this.showError();
						return _callback(false);
					}
				});
				if(!bool) return;
				this.clearError();
				return _callback(true);
			},
			setList(parent,arr){
				if(!arr || !arr.length) return arr;

				let self = this,
					columnName = this.columnName,
					columnObjName = this.columnObjName,
					columnListName = this.columnListName,
					_arr = [];
				$.each(arr,function( i, obj ){
					let _obj = obj[columnObjName],
						_list = obj[columnListName] ,
						_parent , newList;

					_obj.name = _obj[columnName];
					if(_list){
						newList = [];
						_list.forEach(obj=>{
							newList.push(obj[columnObjName]);
						});
					}
					_parent = {
						item : _obj,
						list : newList
					};
					if(_list && _list.length){
						_list = self.setList(_parent,_list);
					}
					_arr.push({
						item : _obj,
						list : _list,
						parent : parent
					});
				});
				return _arr;
			}
		}
	}
</script>
