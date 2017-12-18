<template>
	<div class="form add edit">
		<add-form ref="form" :data="data"></add-form>
		<div class="submit">
			<div class="btn" @click="submit">确定</div>
			<div class="btn red" @click="clearall">清空</div>
		</div>
	</div>
</template>
<script>
	import addForm from "./addForm.vue";
	import { mapGetters } from 'vuex';
	export default {
		components : {
			addForm
		},
		computed : {
			...mapGetters({
				column : 'addColumn'
			})
		},
		props : {
			data : Object
		},
		data() {
			return {
			}
		},
		mounted() {
			const self = this;
		},
		methods:{
			submit(){
				if(!this.$refs.form.verification()) return;
				let column = this.column ,
					name = column.name ,
					path = column.path,
					sort = column.sort,
					description = column.description;
				debugger;
			},
			clearall(){
				this.$refs.form.clearall();
			}
		}

	}
</script>