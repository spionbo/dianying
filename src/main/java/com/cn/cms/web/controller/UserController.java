package com.cn.cms.web.controller;

import com.cn.cms.biz.UserBiz;
import com.cn.cms.contants.StaticContants;
import com.cn.cms.utils.CookieUtil;
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
public class UserController {

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
    @RequestMapping(value = "/loginOut", method = RequestMethod.POST)
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
}
