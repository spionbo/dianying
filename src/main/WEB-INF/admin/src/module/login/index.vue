<style scoped>
    @import 'base.css';
</style>
<template>
    <article class="login-container">
        <div class="login">
            <div class="logo">
                TT528-CMS管理系统
            </div>
            <div class="form">
                <div @click='clearError'>
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
            errorMsgVal(val){
                this.errorMsg = val;
	            this.error = true;
            },
            clearError(){
                this.error = false;
            },
	        vuserName(val){//用户名/，5到16位（字母，数字，下划线，减号）
		        let reg = /^[a-zA-Z0-9_-]{5,16}$/;
		        if (val == '') {
			        return "用户名不能为空";
		        } else if (reg.test(val) == false) { //手机号校验提示
			        return "5-16位(字母,数字,下划线,减号)";
		        }
		        return true;
	        },
	        vpwd(val){
		        let reg = /^(\w){6,20}$/;
		        if(val==''){
			        return '请输入密码';
		        }else if(val.length<6){
			        return '注册密码不能少于6位';
		        }else if(reg.test(val)==false){
			        return '注册密码为6-20位数字、字母或两者组合';
		        }
		        return true;
	        },
            verification(){
                let user = this.vuserName(this.userName),
                    pwd = this.vpwd(this.pwd);

                if(user!==true){
                    return this.errorMsgVal(user);
                }else if(pwd!==true){
                    return this.errorMsgVal(pwd);
                }
                return true;
            },
            submit(){
                const self = this;
                if(self.verification()!==true) return;
                require(['../../plug/md5.min.js'], function(md5) {
                    self.ajax({
                        url : '/user/login' ,
                        type : 'post',
	                    callback : true,
	                    load : true,
                        data : {
                            userName : self.userName,
                            pwd : md5(md5(self.pwd))
                        }
                    }).then(data=>{
                        if(data.code==0){
                            Main.setLoginStatus(true);
                        }else{
                            self.errorMsgVal("用户名或密码错误！");
                        }
                    });
                });

            }
        }
    }
</script>