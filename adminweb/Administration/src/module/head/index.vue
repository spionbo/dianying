/**
*
* created by bo.peng
* email:spion@qq.com
*/
<style>
.header{
    background-color: #545c64;
    color: #fff;
    line-height: 60px;
}
    .aside-right{
        float:right;
    }
    .el-dropdown-link{
        color:#fff;
        cursor:pointer;
        img{
            width:20px;
            height:20px;
            border-radius:100%;
            vertical-align: middle;
        }
    }
</style>
<template>
    <el-menu class="el-menu-demo" mode="horizontal"
             background-color="#545c64"
             text-color="#fff"
             active-text-color="#ffd04b">
        <div class="aside-right">
            <el-dropdown v-if="currentUser" @command="handleCommand">
                <span class="el-dropdown-link">
                    <img :src='currentUser.headImage'>
                    {{currentUser.realName}}<i class="el-icon-arrow-down el-icon--right"></i>
                </span>
                <el-dropdown-menu slot="dropdown">
                    <el-dropdown-item command="updatePwd">更改密码</el-dropdown-item>
                    <el-dropdown-item command="exit">退出</el-dropdown-item>
                </el-dropdown-menu>
            </el-dropdown>
        </div>
        <div class="logo">
            logo
        </div>
    </el-menu>
</template>

<script>
    //import {Menu,MenuItem,Submenu} from "element-ui";
    import {CURRENT_USER_INFO} from "../../contant/URLS";
    import {LOGIN_OUT} from "../../contant/URLS/LOGIN";
	export default {
        components:{
            /*elMenu : Menu,
            elSubmenu : Submenu,
            elMenuItem : MenuItem,*/
        },
        data() {
            return {
                currentUser : null
            }
        },
        mounted() {
            this.getUserInfo();
        },
        methods:{
            getUserInfo(){
                const self = this;
                this.ajax({
                    url:CURRENT_USER_INFO,
                }).then(data=>{
                    self.currentUser = data.data;
                })
            },
            exit(){
                this.ajax({
                    url:LOGIN_OUT,
                }).then(data=>{
                    Main.setLoginStatus(false);
                })
            },
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
            handleCommand(command) {
                this[command]();
            }
        }
	}
</script>
