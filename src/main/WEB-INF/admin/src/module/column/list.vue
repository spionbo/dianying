<style>
	@import 'list.css';
</style>
<template>
	<article class="form horizontal column-list">
		<div class="title">栏目列表</div>
		<div class="content tablist" v-if="list.length">
			<tablist :data="list"></tablist>
		</div>
	</article>
</template>
<script>
	import tablist from './tablist.vue';
	import { mapGetters } from 'vuex';
	export default {
		components:{
			tablist
		},
		computed : {
			...mapGetters({
				list : 'currentMenuPermission'
			})
		}
	}
</script>