<style>
    @import 'base.css';
</style>
<template>
    <article class="login-container">
        <div class="login">
            <div class="logo">
                TT528-CMS管理系统
            </div>
            <div class="form">
                <div class="item">
                    <div class="input">
                        <input type='text' class="text" v-model="userName" placeholder="请输入帐号">
                    </div>
                </div>
                <div class="item">
                    <div class="input">
                        <input type='password' class="text" v-model="pwd" placeholder="请输入密码">
                    </div>
                </div>
                <div class="item">
                    <div class="input">
                        <input type='text' class="text" placeholder='验证码'>
                    </div>
                    <div class="code">
                        <img src="http://www.duorongcf.com/login/validateCode.do">
                    </div>
                </div>
                <div class='msg-error img-size' :class='{cur:error}' data-fontSize='24'>{{errorMsg}}</div>
                <div class="submit">
                    <div class="btn" @click="submit">登录</div>
                </div>
            </div>
        </div>
    </article>
</template>
<script>
    export default {
        data() {
            return {
                userName : '',
                pwd : '',

                errorMsg : '',
                error : false
            }
        },
        mounted() {

            
        },
        methods:{
            submit(){
                const self = this;
                require(['../../plug/md5.min.js'], function(md5) {
                    T.ajax({
                        url : '/webapi/user/login' ,
                        type : 'post',
                        data : {
                            userName : md5(self.userName),
                            pwd : md5(md5(self.pwd))
                        }
                    }).then(data=>{
                        if(data.code==0){
                            
                        }else{
                            
                        }
                    });
                });
                
            }
        }
    }
</script>