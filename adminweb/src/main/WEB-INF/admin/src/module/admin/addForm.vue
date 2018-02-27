<template>
	<ul>
		<li>
			<div class="label">帐号<em>*</em></div>
			<form-input
					type="text"
					dataType="numAndLetter"
					name = "userName"
					:data = "obj.userName"
					:check="true"
					:disabled="obj.disabled"
					maxlength="20"
					placeholder="请输入帐号">
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
					maxlength="20"
					placeholder="请输入密码">
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
					placeholder="请输入真实姓名">
			</form-input>
		</li>
		<li>
			<div class="label">会员头像<em>*</em></div>
			<upload
					name = "imageHead"
					text="请上传头像"
					placeholder="请选择头像"
					:url="obj.imageHead"
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
				//this.obj.password = Math.random().toString(36).substr(2);
				/*解决第一位是数字不通过的情况*/
				let text = "",
				 letter='abcdefghijklmnopqrstuvwxyz',
				 number="0123456789";
				this.obj.password=letter.charAt(Math.floor(Math.random() * letter.length))+Math.random().toString(36).substr(2)+number.charAt(Math.floor(Math.random() * number.length));
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
						userName : data.userName,
						password : "",
						name : data.realName,
						imageHead : data.headImage||"",
						disabled : true
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