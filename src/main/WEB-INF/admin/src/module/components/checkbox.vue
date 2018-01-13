<style scoped>
	.checkbox{
		padding:0 10px;
		border-radius:100px;
		background:#eee;
		color:#666;
		transition-duration:.3s;
		cursor:pointer;
		&:hover{
			background:#00cbfe;
			color:#fff;
		 }
	}
</style>
<template>
	<span class="checkbox">
		<input type="checkbox" v-if="checked" checked />
		<input type="checkbox" v-if="!checked" />
		{{checked?data.right:data.error}}
	</span>
</template>
<script>
	export default {
		props:{
			checked : Boolean,
			data : Object
		},
		data() {
			return {}
		},
		mounted() {

		}
	}
</script>