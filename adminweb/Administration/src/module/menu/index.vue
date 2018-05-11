/**
*
* created by bo.peng
* email:spion@qq.com
*/
<style scoped>
    .aside{
        padding-top:20px;
    }
    .switch{
        text-align:center;
        padding-top:10px;
        padding-bottom:10px;
        font-size:12px;
        border-bottom:1px solid #ddd;
    }
    .menu{
        >li{
            >:first-child{
                &:not(li){
                    border-bottom:1px solid #eee;
                }
            }
        }
        .menu-item{
            &:not(:last-child){
                border-bottom:1px dotted #eee;
            }
        }
        .sub-menu{
            border-bottom:1px dotted #eee;
        }
    }
</style>
<template>
	<Aside width="220px" class="aside">
        <el-menu
            v-if="list"
            :unique-opened="true"
            :router="true"
            default-active="0"
            open="0"
            class="el-menu-vertical-demo menu"
            :collapse="!onOff"
            @open="handleOpen"
            @close="handleClose">
            <div class="switch">
                <el-switch
                    v-model="onOff"
                    :active-text="onOff?'ON':''"
                    :inactive-text="onOff?'OF':''">
                </el-switch>
            </div>
            <el-submenu v-for="(item,index) in list" :index="String(index)" :key="item.id">
                <!-- 一级栏目 -->
                <template class="sub-menu" slot="title">
                    <i :class="item.iconClass"></i>
                    <span>{{item.name}}</span>
                </template>
                <!-- end 一级栏目 -->
                <!-- 二级栏目 -->
                <el-menu-item class="menu-item" @click="go(obj)" v-if="!obj.list" v-for="(obj,index1) in item.list" :index="index+'-'+index1" :key="obj.id">
                    <i class="el-icon-document"></i>
                    <span>{{obj.name}}</span>
                </el-menu-item>
                <!-- end 二级栏目 -->
                <el-submenu class="sub-menu" v-if="obj.list" v-for="(obj,index1) in item.list" :index="index+'-'+index1" :key="obj.id">
                    <template slot="title">
                        <span>{{obj.name}}</span>
                    </template>
                    <!-- 三级栏目 -->
                    <el-menu-item class="menu-item" @click="go(item)" v-if="!item.list" v-for="(item,index2) in obj.list" :index="index+'-'+index1+'-'+index2" :key="item.id">
                        <i class="el-icon-document"></i>
                        <span>{{item.name}}</span>
                    </el-menu-item>
                    <!-- end 三级栏目 -->
                </el-submenu>
            </el-submenu>
        </el-menu>
    </Aside>
</template>

<script>
    import {Aside,Menu,Submenu,MenuItem,MenuItemGroup} from "element-ui";
    import {mapGetters} from "vuex";
	export default {
		components : {
            Aside,
            Menu,
            Submenu,
            MenuItem,
            MenuItemGroup
        },
        computed:{
            ...mapGetters({
                list : "currentMenuPermission",
            })
        },
        data(){
            return {
                onOff : true
            }
        },
        created() {
            this.$store.dispatch("getCurrentMenuPermission");
        },
        methods: {
            handleOpen(key, keyPath) {
                console.log(key, keyPath);
            },
            handleClose(key, keyPath) {
                console.log(key, keyPath);
            },
            go(obj){
                console.log(obj.url);
                router.push(obj.url);
            }
        }
	}
</script>
