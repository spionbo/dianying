<style>
</style>
<template>
    <article>
        <div class="form add">
            <div class="title">新增图片基础分类</div>
            <div class="content">
                <add-form ref="form" :data="obj"></add-form>
                <div class="submit">
                    <div class="btn" @click="submit">确定</div>
                    <div class="btn red">清空</div>
                </div>
            </div>
        </div>
    </article>
</template>
<script>
    import addForm from "./imagesBaseForm.vue";
    import { mapGetters } from 'vuex';
    export default {
        components: {
            addForm
        },
        computed : {
            ...mapGetters(['addColumn'])
        },
        data() {
            return {
                obj : null
            }
        },
        mounted() {
            let self = this;
            this.ajax({
                url : "/images/imagesBase"
            }).then(data=>{
                self.obj = data.data;
            })
        },
        methods:{
            submit(){
                let self = this;
                if(!this.$refs.form.verification()) return;
                if(self.obj && self.obj.id){
                    this.ajax({
                        url : "/images/updateImagesBase",
                        data : {
                            id : self.obj.id,
                            baseUrl : self.addColumn.baseUrl,
                            basePath : self.addColumn.basePath,
                        },
                        type : 'post'
                    }).then(data=>{
                        this.$tips({
                            content:"修改成功!"
                        });
                    })
                }else{
                    this.ajax({
                        url : "/images/createImagesBase",
                        data : {
                            baseUrl : this.addColumn.baseUrl,
                            basePath : this.addColumn.basePath,
                        },
                        type : "post"
                    }).then(data=>{
                        this.$tips({
                            content:"添加成功!"
                        });
                    })
                }

            },
            clearall(){
                this.$refs.form.clearall();
            }
        }
    }
</script>