package com.cn.cms.web.controller;

import com.cn.cms.biz.XiaoshuoBiz;
import com.cn.cms.bo.PermissionBean;
import com.cn.cms.web.ann.CheckAuth;
import com.cn.cms.web.ann.CheckToken;
import com.cn.cms.web.result.ApiResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by Administrator on 2018/1/10 0010.
 * 小说控制层
 */
@Controller
@RequestMapping(value = "/webapi/xiaoshuo/",produces = "application/json;charset=UTF-8")
@ResponseBody
public class XiaoshuoController extends BaseController{

    @Resource
    private XiaoshuoBiz xiaoshuoBiz;

    /**
     * 新增分类
     * @param request
     * @return
     */
    @CheckToken
    @CheckAuth(name = "xiaoshuo:write")
    @RequestMapping(value = "/addClassify",method = RequestMethod.GET)
    public String addClassify(HttpServletRequest request,
                              @RequestParam(value = "name") String name){
        xiaoshuoBiz.saveClassify(name);
        return ApiResponse.returnSuccess();
    }

    @CheckToken
    @CheckAuth(name="xiaoshuo:read")
    @RequestMapping(value = "/")

}
