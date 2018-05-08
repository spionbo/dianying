/**
* 新增作者
* created by bo.peng
* email:spion@qq.com
*/
<style scoped>

</style>
<template>
    <div class="form add">
        <div class="title">新增小说</div>
        <div class="content">
            <author-form ref="form"></author-form>
            <div class="submit">
                <div class="btn" @click="submit">确定</div>
                <div class="btn red">清空</div>
            </div>
        </div>
    </div>
</template>

<script>
    import authorForm from "./form/authorForm.vue";
    import { mapGetters } from 'vuex';
    export default {
        components: {
            authorForm
        },
        computed : {
            ...mapGetters({
                column : 'addColumn'
            })
        },
	}
</script>
