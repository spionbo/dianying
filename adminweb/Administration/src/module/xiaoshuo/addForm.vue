<template>
	<ul>
		<li>
            <div class="label">标题<em>*</em></div>
            <form-input
                type="text"
                name = "name"
                dataType=""
                :data = "obj.title"
                :prompt="prompt"
                maxlength="50"
                placeholder="请输入标题"/>
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
                新增作者
			</m-select>
		</li>
		<li>
			<div class="label">状态<em>*</em></div>
            <m-select ref="status"
                      type="text"
                      :arr="list"
                      name = "status"
                      columnName="status"
                      :columnId = "obj.columnId"
                      columnObjName="permission"
                      columnListName="permissionBeans"
                      dataType="path"
                      :check="[false,false,false]"
                      placeholder="请选择状态">
            </m-select>
		</li>
		<li>
			<div class="label">简介<em>*</em></div>
            <textArea/>
		</li>
		<li>
			<div class="label">内容<em>*</em></div>
			<editor :height="500"></editor>
		</li>
	</ul>
</template>
<script>
	import formInput from "../components/formInput";
	import inputNumber from "../components/inputNumber.vue";
	import textArea from "../components/texta.vue";
	import editor from "../components/editor.vue";
	import mSelect from "../components/selects";
	import { mapGetters } from 'vuex';
	export default {
		components: {
			formInput,
			inputNumber,
            textArea,
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
                prompt:{
                    default : "请输入新的密码，密码为6位数以上必需包含英文和数字",
                    error : "密码为6位数以上必需包含英文和数字",
                    verification( val , callback ){
                        let reg = this._numAndLetter(val);
                        newPwd = val;
                        callback(reg);
                    }
                },
			}
		},
		mounted() {
			this.setData();
			this.list = this.menu;
		},
		methods:{
			verification(){
				let [ischeck] = [true];
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
