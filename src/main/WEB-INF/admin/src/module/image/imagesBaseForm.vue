<template>
	<ul>
		<li>
			<div class="label">基础URL<em>*</em></div>
			<form-input
					type="text"
					dataType="http"
					name = "baseUrl"
					:data = "obj.baseUrl"
					:check="true"
					maxlength="33"
					placeholder="请输入路经名称">
			</form-input>
		</li>
		<li>
			<div class="label">基础路径<em>*</em></div>
			<form-input
					type="text"
					dataType="path"
					name = "basePath"
					:data = "obj.basePath"
					:check="true"
					maxlength="50"
					placeholder="请输入路经名称">
			</form-input>
		</li>
		<li class="eplain">
			<p>1、基础URL和基础路经组合通过图片上传，生成为=基础URL+路经+图片名称(http://www.xx.com/xx/xx.jpg)</p>
			<p>
				2、只能创建1条
			</p>
		</li>
	</ul>
</template>
<script>
	import formInput from "../components/formInput";
    import { mapGetters } from 'vuex';
    export default {
		components: {
			formInput
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
				obj : {}
			}
		},
		mounted() {

		},
		watch:{
		    data( obj ){
		        this.obj = obj;
			}
		},
		methods:{
			verification(){
                return this._verificationForm();
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