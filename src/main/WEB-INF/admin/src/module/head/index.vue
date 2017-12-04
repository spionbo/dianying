<style scoped>
    .header{
        height: 42px;
        line-height:42px;
        padding:0 10px;
        color:#fff;
        background:#212121;
    }
    .user-info{
        float:right;
        padding-right:20px;
        position:relative;
        z-index: 999;
        .list{
            display:none;
            position: absolute;
            right:20px;
            padding:10px 0;
            background:#fff;
            border:1px solid #ccc;

            li{
                padding:0 20px;
                line-height: 22px;
                white-space:nowrap;
                color:#444;
                cursor:pointer;
                text-align:center;
                &:hover{
                    background:#f2f2f2;
                };
            }
        }
        &:hover{
            .list{
                display:block;
            }
        };
    }
    .current-user{
        cursor: pointer;
        img{
            width:30px;
            height:30px;
            border-radius:100%;
            vertical-align: middle;
            margin-top:-2px;
        }
    }
   
</style>
<template>
    <article class="header">
        <div class="user-info" v-if="currentUser">
            <div class="current-user">
                <img :src='currentUser.headImage'>
                <span>{{currentUser.realName}}</span>
            </div>
            <div class="list">
                <ul>
                    <li @click='exit'>
                        <i class="fa fa-sign-out" aria-hidden="true"></i>
                        退出
                    </li>
                </ul>
            </div>
        </div>
    </article>
</template>
<script>
	export default {
		data() {
			return {
                currentUser : null
            }
		},
		mounted() {
            const self = this;
            T.ajax({
                url:"/user/currentUser",
            }).then(data=>{
                self.currentUser = data.data;
            })
		},
        methods :{
            exit(){
                T.ajax({
                    url:"/user/loginOut",
                }).then(data=>{
                    Main.setLoginStatus(false);
                })
            }
        }
	}
</script>