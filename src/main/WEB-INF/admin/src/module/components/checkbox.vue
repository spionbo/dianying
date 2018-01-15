<style scoped>
	@import "style/checkbox.css";
</style>
<template>
	<span class="checkbox" @click="select" :class="{gray:!checked}">
		<!--<input type="checkbox" v-if="checked" checked />
		<input type="checkbox" v-if="!checked" />-->
		<i class="fa fa-check-circle" v-if="checked" checked></i>
		<i class="fa fa-square-o" v-if="!checked"></i>
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
			return {

			}
		},
		mounted() {

		},
		methods:{
			select(){
				this.checked = !this.checked;
			}
		}
	}
</script>