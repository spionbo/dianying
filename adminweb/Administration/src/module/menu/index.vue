/**
*
* created by bo.peng
* email:spion@qq.com
*/
<style scoped>

</style>
<template>
	<Aside width="200px">
        <el-menu
            v-if="list"
            :unique-opened="true"
            :router="true"
            default-active="2"
            class="el-menu-vertical-demo"
            @open="handleOpen"
            @close="handleClose">
            <el-submenu v-for="(item,index) in list" :index="String(index)" :key="item.id">
                <!-- 一级栏目 -->
                <template slot="title">
                    <i :class="item.iconClass"></i>
                    <span>{{item.name}}</span>
                </template>
                <!-- end 一级栏目 -->
                <!-- 二级栏目 -->
                <el-menu-item v-if="!obj.list" v-for="(obj,index1) in item.list" :index="index+'-'+index1" :key="obj.id">
                    <i class="el-icon-document"></i>
                    <span>{{obj.name}}</span>
                </el-menu-item>
                <!-- end 二级栏目 -->
                <el-submenu v-if="obj.list" v-for="(obj,index1) in item.list" :index="index+'-'+index1" :key="obj.id">
                    <template slot="title">
                        <span>{{obj.name}}</span>
                    </template>
                    <!-- 三级栏目 -->
                    <el-menu-item v-if="!item.list" v-for="(item,index2) in obj.list" :index="index+'-'+index1+'-'+index2" :key="item.id">
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
    import {PERMISSION_MENU} from "../../contant/URLS";
	export default {
		components : {
            Aside,
            Menu,
            Submenu,
            MenuItem,
            MenuItemGroup
        },
        data(){
            return {
                list : null
            }
        },
        mounted() {
            const self = this;
            this.ajax({
                url : PERMISSION_MENU,
                type : 'get'
            }).then(data=>{
                let list = data.data;
                list.forEach(obj=>{
                    obj.iconClass = "el-icon-menu";
                    if(obj.powerKey==="Elasticsearch"){
                        obj.iconClass = "el-icon-zoom-in";
                    }
                    if(obj.powerKey==="Backstage"){
                        obj.iconClass = "el-icon-setting";
                    }
                });
                self.list = list;
                self.$store.commit('setCurrentMenuPermission',data.data);
            });
        },
        methods: {
            handleOpen(key, keyPath) {
                console.log(key, keyPath);
            },
            handleClose(key, keyPath) {
                console.log(key, keyPath);
            }
        }
	}
</script>
