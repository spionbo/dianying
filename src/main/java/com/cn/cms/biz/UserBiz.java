package com.cn.cms.biz;

import com.cn.cms.contants.StaticContants;
import com.cn.cms.po.User;
import com.cn.cms.service.UserService;
import com.cn.cms.web.result.ApiResponse;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Administrator on 2017/11/3 0003.
 */
public class UserBiz extends BaseBiz{

    @Resource
    private UserService userService;

    /**
     * WEB
     * 检查用户是否登录。如果登录后则写入Token
     * @param response
     * @param userName
     * @param pwd
     * @return
     */
    public String checkUserAndSetCookie(HttpServletResponse response, String userName, String pwd){
        User user = userService.findUserName(userName);
        pwd = EncryptUtil.encryptPwd(userName,pwd);
        if( user != null ){
            if(user.getPwd().equals(pwd)){
                setCookie(response,user);
                refreshUserCache(user);
                return ApiResponse.returnSuccess(StaticContants.SUCCESS_LOGIN);
            }else{
                return ApiResponse.returnFail(StaticContants.ERROR_PWD);
            }
        }else{
            return ApiResponse.returnFail(StaticContants.ERROR_NO_USER);
        }
    }
}
