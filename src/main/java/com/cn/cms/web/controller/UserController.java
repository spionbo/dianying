package com.cn.cms.web.controller;

import com.cn.cms.biz.UserBiz;
import com.cn.cms.bo.UserBean;
import com.cn.cms.contants.StaticContants;
import com.cn.cms.enums.ErrorCodeEnum;
import com.cn.cms.utils.CookieUtil;
import com.cn.cms.utils.StringUtils;
import com.cn.cms.web.ann.CheckToken;
import com.cn.cms.web.result.ApiResponse;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Administrator on 2017/11/3 0003.
 */

@RestController
@RequestMapping(value = "/webapi/user/",produces = "application/json; charset=UTF-8")
@ResponseBody
public class UserController extends BaseController{

    @Resource
    private UserBiz userBiz;

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
     * 判断是否登录
     * @return
     */
    @RequestMapping(value = "/isLogin",method = RequestMethod.GET)
    public Boolean isLogin(HttpServletRequest request , HttpServletResponse response){
        return userBiz.checkUserToken(request,response);
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
        return ApiResponse.returnSuccess(userBean);
    }
}
