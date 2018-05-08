/**
 * 栏目权限，新装置栏目等
 * created by bo.peng 2018/5/7
 * email:spion@qq.com
 */
const URL = require("./index").URL+"/permission";
module.exports={
    //显示所有栏目菜单
    ALL_COLUMN : URL + "/allColumn",
    //显示栏目信息
    PERMISSION_COLUMN : URL + "/permissionColumn",
    //删除栏目
    PERMISSION_DELETE : URL + "/deleteColumn",
    //新增栏目
    ADD_COLUMN : URL+"/addColumn",
    //删除栏目权限
    DELETE_COLUMN : URL + "/deleteColumn",
};
