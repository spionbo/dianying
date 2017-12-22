<style>
</style>
<template>
	<article>
		<div class="form add">
			<div class="title">新增栏目</div>
			<div class="content">
				<add-form ref="form"></add-form>
				<div class="submit">
					<div class="btn" @click="submit">确定</div>
					<div class="btn red">清空</div>
				</div>
			</div>
		</div>
	</article>
</template>
<script>
	import addForm from "./addForm.vue";
	import { mapGetters } from 'vuex';
	export default {
		components: {
			addForm
		},
		computed : {
			...mapGetters({
				column : 'addColumn'
			})
		},
		data() {
			return {}
		},
		mounted() {

		}
	}
</script>