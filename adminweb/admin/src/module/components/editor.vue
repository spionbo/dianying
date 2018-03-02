<style scoped>
	.iframe{
		margin:0;
		padding:0;
		border:none;
	}
</style>
<template>
	<iframe class="iframe" src="http://localhost:501/"
		:width="width" :height="height"
	></iframe>
</template>
<script>
	export default {
		props:{
			width : Number,
			height : Number
		},
		data() {
			return {}
		},
		mounted() {

		}
	}
</script>