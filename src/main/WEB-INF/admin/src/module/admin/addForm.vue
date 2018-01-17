<template>
	<ul>
		<li>
			<div class="label">帐号<em>*</em></div>
			<form-input
					type="text"
					dataType="numAndLetter"
					name = "userName"
					:data = "obj.letter"
					:check="true"
					maxlength="20"
					placeholder="请输入路经名称">
			</form-input>
		</li>
		<li>
			<div class="label">密码<em>*</em></div>
			<form-input
					type="text"
					dataType="numAndLetter"
					name = "password"
					:data = "obj.password"
					:check="true"
					maxlength="11"
					placeholder="请输入路经名称">
				<div slot='else' class="btn square blue" @click="randPwd">随机生成</div>
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
					name = "imageHead"
					text="请上传头像"
					placeholder="请选择头像"
			></upload>
		</li>
	</ul>
</template>
<script>
	import formInput from "../components/formInput";
	import inputNumber from "../components/inputNumber.vue";
	import texta from "../components/texta.vue";
	import upload from "../components/uploadImage";
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
				column : 'addColumn',
				list : 'currentMenuPermission'
			})
		},
		props : {
			data : Object
		},
		data() {
			return {
				list : null,
				obj : {
					password : ""
				}
			}
		},
		mounted() {
			const self = this;
			self.setData();
		},
		methods:{
			verification(){
				let [self,ischeck] = [this,true];
				this.$children.map(obj=>{
					if(!obj.verification) return;
					obj.verification(function( b ){
						if(!b){ischeck = b};
					});
				});
				if(ischeck){
					return true;
				}
				return false;
			},
			randPwd(){
				this.obj.password = Math.random().toString(36).substr(2);
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