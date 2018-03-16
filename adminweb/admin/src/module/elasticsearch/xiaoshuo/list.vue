<style scoped>
	.elasticsearch{
		.text{
			width:100px;
			height:18px;
			line-height:18px;
			padding:3px 10px;
			border:1px solid #ddd;
			border-radius:20px;
		}
	}
</style>
<template>
	<article>
		<div class="form horizontal elasticsearch">
			<div class="title">小说列表操作</div>
			<div class="content">
				<div class="editor">
					<input type="number" class="text" maxlength="99999" v-model="val"/>
					<div class="btn" @click="updateList">更新数据小说名称</div>
				</div>
				<div class="editor">
					<input type="number" class="text" maxlength="99999" v-model="val1"/>
					<div class="btn" @click="updateXiaoshuoChapter">更新数据小说章节</div>
				</div>
			</div>
		</div>
	</article>
</template>
<script>
	export default {
		components:{
		},
		data() {
			return {
				val : 5,
				val1 : 5
			}
		},
		mounted() {

		},
		methods:{
			updateList(){
				this.ajax({
					url : "/test/updateXiaoshuoList",
					type : "POST",
					data : {
						p : this.val
					}
				}).then(data=>{
					console.log(data);
				})
			},
			updateXiaoshuoChapter(){
				this.ajax({
					url : "/test/updateXiaoshuoChapter",
					type : "POST",
					data : {
						p : this.val1
					}
				}).then(data=>{
					console.log(data);
				})
			}
		}
	}
</script>