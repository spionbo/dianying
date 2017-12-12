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
								name = "name"
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
								:name = "['columnId']"
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
								name = "path"
								:check="true"
								maxlength="11"
								placeholder="请输入路经名称">
						</form-input>
					</li>
					<li>
						<div class="label">排序</div>
						<div class="txt">
							<div class="edit">
								<input type="number" v-model="sort" value="1"/>
							</div>
							<div class="cnt">
								请输入排序，排序为整数
							</div>
						</div>
					</li>
					<li>
						<div class="label">描述</div>
						<div class="txt">
							<div class="edit">
								<textarea class="textarea" v-model="description" style="width:500px;" maxlength="200"></textarea>
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
	import { mapGetters } from 'vuex';
	export default {
		components: {
			formInput,
			mSelect
		},
		computed : {
			...mapGetters({
				column : 'addColumn'
			})
		},
		data() {
			return {
				list : null,

				sort : 1,
				description : ""
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
				this.$pop({
					title :
				});

				return;
				let ischeck = true ,
					column = this.column,
					obj = {
						name : column.name,
						parentId : column.columnId,
						url : column.path,
						sort : this.sort,
						description : this.description
					};

				this.$children.map(obj=>{
					obj.verification(function( b ){
						ischeck = b;
					});
				});
				if(ischeck){
					T.ajax({
						url : '/permission/add',
						type : "POST",
						data : obj
					}).then(data=>{

					})
				}
			}
		}
	}
</script>