<template>
	<ul>
		<li>
			<div class="label">栏目名称<em>*</em></div>
			<form-input
				type="text"
				dataType="chinese"
				name = "name"
				:data = "obj.name"
				:check="true"
				maxlength="6"
				placeholder="请输入栏目名称">
			</form-input>
		</li>
		<li>
			<div class="label">请选择栏目</div>
			<m-select ref="myselect"
				type="text"
				:arr="list"
				name = "selects"
				columnName="name"
				:columnId = "obj.columnId"
				columnObjName="permission"
				columnListName="permissionBeans"
				dataType="path"
				:check="[false,false,false]"
				placeholder="请选择栏目">
			</m-select>
		</li>
		<li>
			<div class="label">路经<em>*</em></div>
			<form-input
				type="text"
				dataType="path"
				name = "path"
				:data = "obj.path"
				:check="true"
				maxlength="20"
				placeholder="请输入路经名称">
			</form-input>
		</li>
		<li>
			<div class="label">排序</div>
			<input-number
				name = "sort"
				:data = "obj.sort"
				min="1"
				max="99">
			</input-number>
		</li>
		<li>
			<div class="label">描述</div>
			<texta
					name="description"
					:data = "obj.description"
					maxlength="100"
					placeholder="描述内容不得超过100字"
			></texta>
		</li>
	</ul>
</template>
<script>
	import formInput from "../components/formInput";
	import inputNumber from "../components/inputNumber.vue";
	import texta from "../components/texta.vue";
	import mSelect from "../components/selects";
	import { mapGetters } from 'vuex';
	export default {
		components: {
			formInput,
			inputNumber,
			texta,
			mSelect
		},
		computed : {
			...mapGetters({
				column : 'addColumn',
				menu : 'currentMenuPermission'
			})
		},
		props : {
			data : Object
		},
		data() {
			return {
				list : null,
				obj : {}
			}
		},
		mounted() {
			this.setData();
			this.list = this.menu;
		},
		methods:{
			verification(){
				let [self,ischeck] = [this,true];
				this.$children.map(obj=>{
					obj.verification(function( b ){
						if(!b){ischeck = b};
					});
				});
				if(ischeck){
					return true;
				}
				return false;
			},
			setData(){
				let [
					self,
					data
				] = [
					this,
					this.data
				];
				if(data){
					self.obj = {
						name : data.name,
						columnId : data.id,
						sort : data.sort,
						description : data.description,
						path : data.url.match(/\/\w+$/)[0]
					};
				}
			},
			clearall(){
				this.obj = {};
				this.$children.forEach(obj=>{
					obj.clear();
				})
			}
		}
	}
</script>