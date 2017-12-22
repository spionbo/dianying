<style>
</style>
<template>
	<article>
		<div class="form add">
			<div class="title">新增图片分类</div>
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
	export default {
		components: {
			addForm
		},
		data() {
			return {}
		},
		mounted() {

		}
	}
</script>