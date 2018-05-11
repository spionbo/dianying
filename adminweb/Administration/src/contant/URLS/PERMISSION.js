/**
 * 栏目权限，新装置栏目等
 * created by bo.peng 2018/5/7
 * email:spion@qq.com
 */
const URL = require("./index").URL+"/permission";
module.exports={
    //显示当前用户的栏目信息
    CURRENT_USER_PERMISSION_COLUMN : URL + "/currentMenuPermission",
    //当前栏目信息
    CURRENT_PERMISSION_COLUMN : URL + "/permissionColumnInfo",
};
