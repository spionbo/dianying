/**
 * 管理员管理
 * created by bo.peng 2018/5/8
 * email:spion@qq.com
 */
const URL = require("./index").URL+"/user";
module.exports={
    //更新用户信息
    UPDATE : URL + '/update',
    //获取用户有列表
    USER_LIST : URL+"/userlist",
    //删除用户
    DELETE : URL + "/delete",
    //更新用户栏目权限
    UPDATE_USER_COLUMN_POWER : "/updateUserColumnPower",
    //获取用户栏目权限
    GET_USER_PERMISSION_POWER : "/userPermissionPower",
};
