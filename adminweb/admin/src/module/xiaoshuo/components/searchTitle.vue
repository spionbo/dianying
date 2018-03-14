<style scoped>
	.txt{
		input{
			width:250px;
			height:18px;
			line-height:18px;
			padding:5px;
			border:1px solid #ddd;
		}
	}

</style>
<template>
	<div class="txt">
		<input
			type="text"
			placeholder="请输入标题"
			v-model="value"
			maxlength="50"
		>
		</input>
	</div>
</template>
<script>
	export default {
		props: {
		},
		data() {
			return {
				value : "",

				error : false,
				isSearch : false,
			}
		},
		watch : {
			value:function( val ){
				this.search(val);
			}
		},
		mounted() {

		},
		methods:{
			search( val ){
				let self = this;
				if(!this.isSearch){
					//this.isSearch = true;
					this.ajax({
						url:'/search/searchXiaoshuoChapter',
						type : "get"
					}).then(data=>{
						self.isSearch = false;
					})
				}

			}
		}
	}
</script>