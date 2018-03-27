<style>
	@import './style/search.css';
	.w100{ width:100px; }
	.w120{ width:120px; }
</style>
<template>
	<article>
		<div class="search-box">
			<input class="text" v-model="title" placeholder="请输入标题"/>
			<input class="text w100" v-model="author" placeholder="请输入作者"/>
			<input class="text" v-model="setLastModifyUserId" placeholder="请输入修改人ID"/>
			<input class="text" v-model="lastModifyUserName" placeholder="请输入修改人名称"/>
			<input class="text" v-model="id" placeholder="请输入文章id"/>
			<select class="select" v-model="classifyId">
				<option value="-1">请选择分类</option>
				<option>更新中</option>
			</select>
			<select class="select" v-model="status">
				<option value="-1">请选择状态</option>
				<option>更新中</option>
				<option>已完结</option>
			</select>
			<input class="date w120" type="date"/> - <input class="date w120" type="date"/>
			<div class="btn" @click="search">搜索</div>
		</div>
		<div class="form horizontal userlist">
			<div class="title">分类列表</div>
			<list></list>
		</div>
	</article>
</template>
<script>
	import formInput from "../components/formInput";
	import list from './components/list.vue';
	export default {
		components:{
			formInput,
			list
		},
		data() {
			return {
				id : "",
				title : "",
				author : "",
				classifyId : -1,
				status : -1,
				setLastModifyUserId : "",
				lastModifyUserName : "",
				isSearch : false,
			}
		},
		watch:{
			title(val){

			}
		},
		mounted() {

		},
		methods:{
			search(){
				return;
				if(!this.isSearch){
					this.isSearch = true;
					this.ajax({
						url:'/search/searchXiaoshuoAbout',
						data : {
							title : this.title,
							id : "",
							classifyId : "",
							author : "",
							status : "",
							setLastModifyUserId : "",
							lastModifyUserName : "",
						},
						type : "get"
					}).then(data=>{
						self.isSearch = false;
						self.setPositionVal(data);
					})
				}
			}
		}
	}
</script>