<style>
	@import './style/search.css';
</style>
<template>
	<article>
		<div class="search-box">
			<search-title></search-title>
		</div>
		<div class="form horizontal userlist">
			<div class="title">分类列表</div>
			<list></list>
		</div>
	</article>
</template>
<script>
	import searchTitle from './components/searchTitle.vue';
	import list from './components/list.vue';
	export default {
		components:{
			searchTitle,
			list
		},
		data() {
			return {
			}
		},
		mounted() {

		},
		methods:{

		}
	}
</script>