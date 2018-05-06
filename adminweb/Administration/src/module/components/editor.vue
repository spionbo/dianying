<style scoped>
	.iframe{
		margin:0;
		padding:0;
		border:none;
	}
    .text{
        .auto{
            padding:0;
        }
    }
</style>
<template>
    <article class="txt">
        <iframe class="iframe" src="../../../static/ueditor1_4_3_3-utf8-jsp"
            width="100%" :height="height"
        ></iframe>
    </article>
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
