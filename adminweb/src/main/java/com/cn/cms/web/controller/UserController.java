package com.cn.cms.web.controller;

import com.cn.cms.biz.PermissionBiz;
import com.cn.cms.biz.UserPowerBiz;
import com.cn.cms.biz.UserBiz;
import com.cn.cms.bo.UserBean;
import com.cn.cms.contants.PermissionNames;
import com.cn.cms.enums.ErrorCodeEnum;
import com.cn.cms.exception.BizException;
import com.cn.cms.po.User;
import com.cn.cms.po.UserPower;
import com.cn.cms.utils.EncryptUtil;
import com.cn.cms.utils.Page;
import com.cn.cms.utils.StringUtils;
import com.cn.cms.web.ann.CheckAuth;
import com.cn.cms.web.ann.CheckToken;
import com.cn.cms.web.result.ApiResponse;
import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 用户登录等操作
 * Created by Administrator on 2017/11/3 0003.
 */

@RestController
@RequestMapping(value = "/webapi/user/",produces = "application/json; charset=UTF-8")
@ResponseBody
public class UserController extends BaseController{

    @Resource
    private UserBiz userBiz;

    @Resource
    private PermissionBiz permissionBiz;

    @Resource
    private UserPowerBiz userPowerBiz;

    /**
     * 登录
     * @param response
     * @param userName
     * @param pwd
     * @return
     */
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public String login(HttpServletResponse response,
                        @RequestParam(value = "userName")String userName,
                        @RequestParam(value = "pwd")String pwd){
        return userBiz.checkUserAndSetCookie(response,userName,pwd);
    }

    /**
     * 登出
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/loginOut", method = RequestMethod.GET)
    public String loginOut(HttpServletRequest request , HttpServletResponse response){
        userBiz.clearCookie(request, response);
        return ApiResponse.returnSuccess();
    }

    /**
     * 获取当前登录用户信息
     * @param request
     * @return
     */
    @RequestMapping(value = "/currentUser",method = RequestMethod.GET)
    @CheckToken
    public String currentUser(HttpServletRequest request){
        String userID = getCurrentUserId(request);
        if(StringUtils.isBlank(userID)){
            return ApiResponse.returnFail(ErrorCodeEnum.ERROR_LOGIN_FAIL.getType(),ErrorCodeEnum.ERROR_LOGIN_FAIL.getMessage());
        }
        UserBean userBean = userBiz.getUserBean(userID);

        RestHighLevelClient client = new RestHighLevelClient(
                RestClient.builder(
                        new HttpHost("localhost", 9200, "http")
                )
        );

         return ApiResponse.returnSuccess(userBean);
    }

    /**
     * 获取管理员列表
     * @param request
     * @param page
     * @param pageSize
     * @return
     */
    @CheckToken
    @CheckAuth( name = PermissionNames.BACKSTAGE.ADMIN.LIST.READ)
    @RequestMapping(value = "/userlist",method = RequestMethod.GET)
    public String list(HttpServletRequest request,
                       @RequestParam(value = "page",required = false) Integer page ,
                       @RequestParam(value = "pageSize",required = false) Integer pageSize){
        Page pageObj = new Page(page,pageSize);
        List<UserBean> users= userBiz.listUser(pageObj);
        userBiz.dataInit(users);
        Map<String, Object> result = new HashMap<>();
        result.put("page",pageObj);
        result.put("list",users);
        return ApiResponse.returnSuccess(result);
    }

    /**
     * 获取用户权限 读 写 更 删
     * @param userId
     * @return
     */
    @CheckToken
    @CheckAuth(name = PermissionNames.BACKSTAGE.ADMIN.LIST.READ)
    @RequestMapping(value = "/userPermissionPower",method=RequestMethod.GET)
    public String getUserPermissionPower(HttpServletRequest request,
                                      @RequestParam(value = "userId") String userId){
        List<UserPower> userPower = userPowerBiz.getUserPower(userId);
        return ApiResponse.returnSuccess(userPower);
    }

    /**
     * 更新用户查看栏目的权限权限
     * @param request
     * @param power
     * @return
     */
    @CheckToken
    @CheckAuth(name = PermissionNames.BACKSTAGE.ADMIN.LIST.UPDATE)
    @RequestMapping(value = "/updateUserColumnPower",method = RequestMethod.POST)
    public String updateUserPermission(HttpServletRequest request,
                                       @RequestParam(value = "power") String power,
                                       @RequestParam(value = "userId") String userId){
        userPowerBiz.updateUserPower(power,userId);
        return ApiResponse.returnSuccess();
    }

    /**
     * 新增管理员 则添加权限数据库，
     * @param request
     * @param userName
     * @param password
     * @param realName
     * @param imageHead
     * @return
     */
    @CheckToken
    @CheckAuth(name = PermissionNames.BACKSTAGE.ADMIN.ADD.WRITE)
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public String add(HttpServletRequest request,
                      @RequestParam(value = "userName") String userName,
                      @RequestParam(value = "password")  String password,
                      @RequestParam(value = "realName") String realName,
                      @RequestParam(value = "imageHead",required = false) String imageHead,
                      @RequestParam(value = "updateUserName",required = false) String updateUserName) throws BizException{
        String userID = getCurrentUserId(request);
        Integer a = userBiz.queryUserName(userName);
        if(a>0){
            return ApiResponse.returnFail(ErrorCodeEnum.ERROR_USERNAME_RE.getType(),ErrorCodeEnum.ERROR_USERNAME_RE.getMessage());
        }
        userBiz.createUser(userName,password,realName,imageHead,userID,updateUserName);
        return ApiResponse.returnSuccess();
    }

    /**
     * 更新 用户信息
     * @param request
     * @param userId
     * @param realName
     * @param headImage
     * @param pwd
     * @return
     */
    @CheckToken
    @CheckAuth(name = PermissionNames.BACKSTAGE.ADMIN.LIST.UPDATE)
    @RequestMapping(value = "/update",method = RequestMethod.POST)
    public String updateUser(HttpServletRequest request,
                             @RequestParam("userId") String userId,
                             @RequestParam("userName") String userName,
                             @RequestParam(value = "realName",required = false) String realName,
                             @RequestParam(value = "headImage",required = false) String headImage,
                             @RequestParam(value = "pwd",required = false) String pwd){

        UserBean thisUser = userBiz.getUserBean(userId);
        if(thisUser.getUserId()==null){
            UserBean userBean = userBiz.getUserInfo(userId);
            if(userBean.getUserId()==null){
                return ApiResponse.returnFail(ErrorCodeEnum.ERROR_USER_HASTRUE.getType(),ErrorCodeEnum.ERROR_USER_HASTRUE.getMessage());
            }
        }
        userBiz.updateUser(getCurrentUserId(request),userId,userName, realName, headImage, pwd);
        return ApiResponse.returnSuccess();
    }

    @CheckToken
    @RequestMapping(value = "/updateInfo",method = RequestMethod.POST)
    public String updateInfo(HttpServletRequest request,
                             @RequestParam(value = "userName",required = false) String userName,
                             @RequestParam(value = "realName",required = false) String realName,
                             @RequestParam(value = "headImage",required = false) String headImage,
                             @RequestParam(value = "pwd") String pwd,
                             @RequestParam(value = "pwd1") String pwd1,
                             @RequestParam(value = "pwd2") String pwd2){
        String userId = getCurrentUserId(request);
        User user = userBiz.getUserCache(userId);
        String thispwd = EncryptUtil.encryptPwd(userName,pwd);
        if((pwd1.equals(pwd2)) && user.getPwd().equals(thispwd)){
            userBiz.updateUserInfo(userId,userName,realName,headImage,pwd1);
            return ApiResponse.returnSuccess();
        };
        return ApiResponse.returnFail(ErrorCodeEnum.ERROR_USER_PWD.getType(),ErrorCodeEnum.ERROR_USER_PWD.getMessage());
    }

    /**
     * 删除用户，则需要删除用户权限
     * @param request
     * @param userId
     * @return
     * @throws BizException
     */
    @CheckToken
    @CheckAuth(name = PermissionNames.BACKSTAGE.ADMIN.LIST.DELETE)
    @RequestMapping(value = "/delete",method = RequestMethod.POST)
    public String deleteUser(HttpServletRequest request,
                              @RequestParam(value = "userId") String userId) throws BizException{
        userBiz.deleteUser(userId);
        userPowerBiz.deleteUserPower(userId);
        return ApiResponse.returnSuccess();
    }
}
