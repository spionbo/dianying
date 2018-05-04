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
			placeholder="请输入小说标题"
			v-model="value"
			maxlength="50"
		/>
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
			this.setPositionEle();
		},
		methods:{
			setPositionEle(){
				let className = "t"+new Date().getTime();
				$(`
					<div class="${className}" v-if="list.length">
					</div>
				`).appendTo("body");
				this.position = new Vue({
					el: '.'+className,
					data(){
						return {
							list : []
						}
					},
					mounted(){

					},
					methods:{
						setList(data){
							debugger;
						}
					}
				});
			},
			setPositionVal(data){
				this.position.setList(data);
			},
			search( val ){
				let self = this;
				if(!this.isSearch){
					this.isSearch = true;
					this.ajax({
						url:'/search/searchXiaoshuoAbout',
						data : {
							title : val
						},
						type : "get"
					}).then(data=>{
						self.isSearch = false;
						self.setPositionVal(data);
					})
				}

			}
		}
	}
</script>