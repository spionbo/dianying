<template>
	<ul>
		<li>
			<div class="label">小说标题</div>
			<searchInput
					placeholder="请输入小说标题进行搜索"
			>

			</searchInput>
		</li>
		<li>
			<div class="label">请选择分类</div>
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
			<div class="label">标题<em>*</em></div>
			<form-input
				type="text"
				dataType="chinese"
				name = "name"
				:data = "obj.name"
				:check="true"
				maxlength="10"
				placeholder="请输入栏目名称">
			</form-input>
		</li>
		<li>
			<div class="label">作者<em>*</em></div>
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
			新增作者
		</li>
		<li>
			<div class="label">状态<em>*</em></div>
			<form-input
					type="text"
					dataType="chinese"
					name = "name"
					:data = "obj.name"
					:check="true"
					maxlength="10"
					placeholder="请输入栏目名称">
			</form-input>
		</li>
		<li>
			<div class="label">简介<em>*</em></div>

		</li>
		<li>
			<div class="label">内容<em>*</em></div>
			<editor :width="width" :height="500"></editor>
		</li>
	</ul>
</template>
<script>
	import formInput from "../components/formInput";
	import inputNumber from "../components/inputNumber.vue";
	import texta from "../components/texta.vue";
	import editor from "../components/editor.vue";
	import mSelect from "../components/selects";
	import searchInput from "../components/xiaoshuo/searchInput";
	import { mapGetters } from 'vuex';
	export default {
		components: {
			formInput,
			searchInput,
			inputNumber,
			texta,
			editor,
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
				obj : {},
				width : window.innerWidth-500
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