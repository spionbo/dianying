<template>
	<ul>
		<li>
            <div class="label">标题<em>*</em></div>
            <form-input
                type="text"
                name = "title"
                dataType=""
                :data = "obj.title"
                :width="400"
                :prompt="prompt"
                :check="true"
                maxlength="50"
                placeholder="请输入标题"/>
		</li>
		<li>
			<div class="label">请选择分类</div>
			<m-select ref="myselect"
			          type="text"
			          :arr="classifyList"
			          name = "selects"
			          :columnId = "obj.columnId"
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
            <!--<m-select ref="status"
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
            </m-select>-->
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
	import formInput from "../../components/formInput";
	import inputNumber from "../../components/inputNumber.vue";
	import textArea from "../../components/texta.vue";
	import editor from "../../components/editor.vue";
	import mSelect from "../../components/selects";
	import {CLASSIFY_LIST} from "../../../contant/URLS/XIAOSHUO";
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
                classifyList : null,//分类
				obj : {},
                prompt:{
                    default : "标题在5-50个字符之间",
                    error : "错误，标题在5-50个字符之间",
                    verification( val , _callback ){
                        _callback(val.length>5 && val.length<50);
                    }
                },
			}
		},
		mounted() {
		    let self = this;
			this.setData();
			this.list = this.menu;
			this.ajax({
                url : CLASSIFY_LIST
            }).then(data=>{
                let arr = data.data.list;
                arr.forEach(obj=>{
                    obj.name = obj.classifyName
                });
                self.classifyList = arr;
            })
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
