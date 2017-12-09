<style>
</style>
<template>
	<article>
		<div class="form add">
			<div class="title">新增栏目</div>
			<div class="content">
				<ul>
					<li>
						<div class="label">栏目名称<em>*</em></div>
						<form-input
								type="text"
								dataType="chinese"
								:check="true"
								maxlength="5"
								placeholder="请输入栏目名称">
						</form-input>
					</li>
					<li>
						<div class="label">请选择栏目<em>*</em></div>
						<m-select
								type="text"
								:data="list"
								columnName="name"
								columnObjName="permission"
								dataType="path"
								:check="true"
								placeholder="请选择分类">
						</m-select>
					</li>
					<li>
						<div class="label">路经<em>*</em></div>
						<form-input
								type="text"
								dataType="path"
								:check="true"
								maxlength="11"
								placeholder="请输入路经名称">
						</form-input>
					</li>
					<li>
						<div class="label">描述</div>
						<div class="txt">
							<div class="edit">
								<textarea class="textarea" style="width:500px;" maxlength="200"></textarea>
							</div>
							<div class="cnt">
								其他内容
							</div>
						</div>
					</li>
				</ul>
				<div class="submit">
					<div class="btn" @click="submit">确定</div>
					<div class="btn red">清空</div>
				</div>
			</div>
		</div>
	</article>
</template>
<script>
	import formInput from "../components/formInput";
	import mSelect from "../components/select";
	export default {
		components: {
			formInput,
			mSelect
		},
		data() {
			return {
				list : null,
			}
		},
		mounted() {
			const self = this;
			T.ajax({
				url:'/permission/currentMenuPermission',
			}).then(data=>{
				self.list = data.data;
			});
		},
		methods:{
			submit(){
				this.$children.map(obj=>{
					obj.verification && obj.verification();
				});
			}
		}
	}
</script>