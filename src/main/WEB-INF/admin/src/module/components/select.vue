<style>
</style>
<template>
	<div class="txt">
		<div class="select">
			<select v-model="selected1" id="select1">
				<option value="-1">{{placeholder}}</option>
				<option v-for="item in list" :key="item.item.id"
				        v-bind:value="item.item.id"
				>
					{{item.item.name}}
				</option>
			</select>
			<select v-model="selected2" v-if="section2.length" id="select2">
				<option value="-1" selected>{{placeholder}}</option>
				<option v-for="item in section2" :key="item.item.id"
				        v-bind:value="item.item.id"
				>
					{{item.item.name}}
				</option>
			</select>
			<select v-model="selected3" v-if="section3.length" id="select3">
				<option value="-1" selected>{{placeholder}}</option>
				<option v-for="item in section3" :key="item.item.id"
				        v-bind:value="item.item.id"
				>
					{{item.item.name}}
				</option>
			</select>
		</div>
		<div class="cnt" :class="{cur:error}">
			请选择分类
		</div>
	</div>
</template>
<script>
	import myMixin from "../../common/mixin";
	export default {
		mixins : [myMixin],
		props:{
			columnId : Number ,
			arr : Array,
			columnName : String, //数组名称转换
			columnObjName : String, //数组对像名称转换
			columnListName : String,//数组列表名称转换
			name : Array,

			placeholder : String,
			check : Boolean,
		},
		data() {
			return {
				list : [],

				section2 : [],
				section3 : [],
				section4 : [],

				selected1 : -1,
				selected2 : -1,
				selected3 : -1,

				error : false
			}
		},
		watch:{
			selected1(val){
				const self = this;
				self.list.map(obj=>{
					if(obj.item.id == val){
						self.$store.commit("setCatch",{
							[this.name[0]] : obj.item
						});
					}
				});
				self.section2 = [];
				if(this.columnListName){
					this.list.map(obj=>{
						if(obj.item.id == val){
							if(obj.list.length){
								self.section2 = obj.list;
								self.setFirstOption("select2");
							}
						}
					});
				};
				this.verification();
			},
			selected2(val){
				const self = this;
				self.section3 = [];
				this.$store.commit("setCatch",{
					[this.name[1]] : val
				});
				this.section2.map(obj=>{
					if(obj.item.id == val){
						if(obj.list.length){
							self.section3 = obj.list;
							self.setFirstOption("select3");
						}
					}
				});
				this.verification();

			},
			selected3(val){
				const self = this;
				self.section4 = [];
				this.$store.commit("setCatch",{
					[this.name[2]] : val
				});
				this.section3.map(obj=>{
					if(obj.item.id == val){
						if(obj.list.length){
							self.section4 = obj.list;
							self.setFirstOption("select4");
						}
					}
				});
				this.verification();
			},
			arr(val){
				this.list = this.setList(this.arr);
			},
			columnId(val){
				debugger;
			}
		},
		mounted() {

		},
		methods : {
			showError(){
				this.error = true;
			},
			clearError(){
				this.error = false;
			},
			setFirstOption( demo ){
				this.$nextTick(function(){
					document.getElementById(demo).selectedIndex=0;
				});
			},
			verification(callback){
				callback = callback || function(){};
				if(this.check){
					if(this.selected1==-1){
						this.showError();
						return callback(false);
					}
					if(this.selected2==-1 && this.section2.length){
						this.showError();
						return callback(false);
					}
					if(this.selected3==-1 && this.section3.length){
						this.showError();
						return callback(false);
					}
				}
				this.clearError();
				return callback(true);
			},
			setList(arr){
				if(!arr || !arr.length) return arr;
				let self = this,
					columnName = this.columnName,
					columnObjName = this.columnObjName,
					columnListName = this.columnListName,
					_arr = [];
				$.each(arr,function( i, obj ){
					let _obj = obj[columnObjName],
						_list = obj[columnListName];

					_obj.name = _obj[columnName];
					if(_list && _list.length){
						_list = self.setList(_list);
					}
					_arr.push({
						item : _obj,
						list : _list
					});
				});
				return _arr;
			}
		}
	}
</script>