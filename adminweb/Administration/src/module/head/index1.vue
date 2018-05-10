<style scoped>
    @import "base.css";
</style>
<template>
    <Header class="header">
        <div class="user-info" v-if="currentUser">
            <div class="current-user">
                <img :src='currentUser.headImage'>
                <span>{{currentUser.realName}}</span>
            </div>
            <div class="list">
                <ul>
	                <li @click='updatePwd'>
		                <i class="fa fa-lock" aria-hidden="true"></i>
		                更改密码
	                </li>
                    <li @click='exit'>
                        <i class="fa fa-sign-out" aria-hidden="true"></i>
                        退出
                    </li>
                </ul>
            </div>
        </div>
	    <div class="leftside">
			<div class="logo">
				<span>TT528</span>
				后台管理
			</div>
	    </div>
    </Header>
</template>
<script>
    import {CURRENT_USER_INFO} from "../../contant/URLS";
    import {LOGIN_OUT} from "../../contant/URLS/LOGIN";
	export default {
		data() {
			return {
                currentUser : null
            }
		},
		mounted() {
            const self = this;
            this.ajax({
                url:CURRENT_USER_INFO,
            }).then(data=>{
                self.currentUser = data.data;
            })
		},
        methods :{
	        updatePwd(){
	        	let self = this;
		        require.ensure([],(require)=> {
			        this.$requirePop(require('./edit'), {
					        props : {
						        data : self.currentUser,
					        }
				        },
				        {
					        props: {
						        obj: {
							        title: "更改密码",
							        close: true,
						        }
					        }
				        }
			        );
		        });
	        },
            exit(){
                this.ajax({
                    url:LOGIN_OUT,
                }).then(data=>{
                    Main.setLoginStatus(false);
                })
            }
        }
	}
</script>
