<template>
	<ul>
		<li>
			<div class="label">帐号<em>*</em></div>
			<form-input
					type="text"
					dataType="numOrLetter"
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
					type="password"
					dataType="password"
					name = "password"
					:check="true"
					maxlength="11"
					placeholder="请输入密码">
			</form-input>
		</li>
		<li>
			<div class="label">新密码<em>*</em></div>
			<form-input
					type="password"
					name = "pwd1"
					dataType="pwd1"
					:check="true"
					:prompt="prompt"
					maxlength="11"
					placeholder="请输入新密码">
			</form-input>
		</li>
		<li>
			<div class="label">新密码<em>*</em></div>
			<form-input
					type="password"
					name = "pwd2"
					dataType="pwd2"
					:prompt="prompt1"
					:check="true"
					maxlength="11"
					placeholder="请再次输入新密码">
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
	let newPwd;
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
				},
				prompt:{
					default : "请输入新的密码，密码为6位数以上必需包含英文和数字",
					error : "密码为6位数以上必需包含英文和数字",
					verification( val , callback ){
						let reg = this._numAndLetter(val);
						newPwd = val;
						callback(reg);
					}
				},
				prompt1:{
					default : "请再次输入密码",
					error : "两次密码不一致",
					verification( val , callback ){
						callback(val===newPwd);
					}
				}
			}
		},
		mounted() {
			const self = this;
			self.setData();
		},
		methods:{
			verification(){ //用于父类调用
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
			setData(){
				let [
					self,
					data
				] = [
					this,
					this.data
				];
				self.obj = {
					userName : data.userName,
					password : "",
					name : data.realName,
					imageHead : data.headImage||"",
					disabled : true
				};
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