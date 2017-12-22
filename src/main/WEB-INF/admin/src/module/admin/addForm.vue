<template>
	<ul>
		<li>
			<div class="label">帐号<em>*</em></div>
			<form-input
					type="text"
					dataType="path"
					name = "path"
					:data = "obj.path"
					:check="true"
					maxlength="11"
					placeholder="请输入路经名称">
			</form-input>
		</li>
		<li>
			<div class="label">密码<em>*</em></div>
			<form-input
					type="password"
					dataType="path"
					name = "path"
					:data = "obj.path"
					:check="true"
					maxlength="11"
					placeholder="请输入路经名称">
			</form-input>
		</li>
		<li>
			<div class="label">真实姓名<em>*</em></div>
			<form-input
					type="text"
					dataType="chinese"
					name = "name"
					:data = "obj.name"
					:check="true"
					maxlength="5"
					placeholder="请输入栏目名称">
			</form-input>
		</li>
		<li>
			<div class="label">会员头像<em>*</em></div>
			<upload
					text="上传头像"
					placeholder="请选择头像"
			></upload>
		</li>
		<li>
			<div class="label">会员权限<em>*</em></div>
			<form-input
					type="text"
					dataType="chinese"
					name = "name"
					:data = "obj.name"
					:check="true"
					maxlength="5"
					placeholder="请输入栏目名称">
			</form-input>
		</li>
	</ul>
</template>
<script>
	import formInput from "../components/formInput";
	import inputNumber from "../components/inputNumber.vue";
	import texta from "../components/texta.vue";
	import upload from "../components/upload";
	import { mapGetters } from 'vuex';
	export default {
		components: {
			formInput,
			inputNumber,
			texta,
			upload
		},
		computed : {
			...mapGetters({
				column : 'addColumn'
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
			const self = this;
			this.ajax({
				url:'/permission/currentMenuPermission',
			}).then(data=>{
				self.list = data.data;
				self.setData();
			});

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
					/*self.$nextTick(function(){ //myselect 更新数据后，获取他的值
						let arr = self.$refs.myselect.selects,
							len = arr.length - 1 ,
							path = data.url;
						for(let i=0;i<len;i++){
							path = path.replace(arr[i].url,"");
						}
						self.obj.path = path;
					})*/
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