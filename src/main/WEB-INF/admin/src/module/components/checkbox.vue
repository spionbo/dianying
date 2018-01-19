<style scoped>
	@import "style/checkbox.css";
</style>
<template>
	<span class="checkbox" @click="select" :class="{gray:!selected}">
		<i class="fa fa-check-circle" v-if="selected" checked></i>
		<i class="fa fa-square-o" v-if="!selected"></i>
		{{selected?data.right:data.error}}
	</span>
</template>
<script>
	export default {
		props:{
			checked : Boolean,
			name : String,
			data : Object,
			parentData : Object
		},
		data() {
			return {
				selected : false
			}
		},
		mounted() {
			this.selected = this.checked;
		},
		methods:{
			select(){
				this.selected = !this.selected;
				this.$store.commit("setCatch",{
					[this.name] : this.selected
				});
				this.$emit("checkEvent",this.selected,this.data,this.parentData);
			}
		}
	}
</script>