/**
 * 权限相关
 * created by bo.peng 2018/5/11
 * email:spion@qq.com
 */
import {USER_CURRENT_MENU_PERMISSION} from "../../contant/CATCH/LOCALSTORAGE/PERMISSION";
const URL = require("../../contant/URLS/PERMISSION");
export default{
    state : {
        userCurrentPermissionColumn : null,
    },
    mutations:{
        _getCurrentMenuPermission( state ){
            this._vm.ajax({
                url : URL.CURRENT_USER_PERMISSION_COLUMN
            }).then(obj=>{
                let list = obj.data;
                list.forEach(obj=>{
                    obj.iconClass = "el-icon-menu";
                    if(obj.powerKey==="Elasticsearch"){
                        obj.iconClass = "el-icon-zoom-in";
                    }
                    if(obj.powerKey==="Backstage"){
                        obj.iconClass = "el-icon-setting";
                    }
                });
                state.userCurrentPermissionColumn = list;
                T.setItem(USER_CURRENT_MENU_PERMISSION,list);
            });
        }
    },
    actions:{
        getCurrentMenuPermission({commit,state}){//获取当前用户栏目权限
            let data = T.getItem(USER_CURRENT_MENU_PERMISSION);
            if(data){
                state.userCurrentPermissionColumn = data;
            }else{
                commit("_getCurrentMenuPermission");
            }
        },
        updateCurrentMenuPermission({commit}){ //更新
            commit("_getCurrentMenuPermission");
        }
    },
    getters:{
        currentMenuPermission : state=>{
            return state.userCurrentPermissionColumn;
        }
    }
};
