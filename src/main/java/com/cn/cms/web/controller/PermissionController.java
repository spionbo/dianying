package com.cn.cms.web.controller;

import com.cn.cms.biz.PermissionBiz;
import com.cn.cms.bo.PermissionBean;
import com.cn.cms.web.ann.CheckToken;
import com.cn.cms.web.result.ApiResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by Administrator on 2017/11/30 0030.
 */
@Controller
@RequestMapping(value = "/webapi/permission/",produces = "application/json;charset=UTF-8")
@ResponseBody
public class PermissionController extends BaseController{

    @Resource
    private PermissionBiz permissionBiz;

    /**
     * 获取用户拥有的菜单栏权限
     * @param request
     * @return
     */

    @CheckToken
    @RequestMapping(value = "/currentMenuPermission",method = RequestMethod.GET)
    public String currentMenuPermission(HttpServletRequest request){
        String userID = getCurrentUserId(request);
        List<PermissionBean> list = permissionBiz.getMenuPermission(userID);
        return ApiResponse.returnSuccess(list);
    }

}
