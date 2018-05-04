/**
 * 常见功能
 * created by bo.peng
 * email:spion@qq.com
 */
const URL = "/adminWeb/webapi";
module.exports={
    URL : URL,
    //栏目权限列表
    PERMISSION_MENU : URL + "/permission/currentMenuPermission",
    //获取当前用户信息
    CURRENT_USER_INFO : URL + "/user/currentUser",
};
