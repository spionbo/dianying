package com.cn.cms.web.controller;

import com.cn.cms.biz.UserBiz;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
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

}
