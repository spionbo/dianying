package com.cn.cms.web.controller;

import com.cn.cms.biz.ColumnBiz;
import com.cn.cms.bo.ColumnBean;
import com.cn.cms.bo.PermissionBean;
import com.cn.cms.contants.PermissionNames;
import com.cn.cms.po.Permission;
import com.cn.cms.po.SecondColumn;
import com.cn.cms.service.ColumnService;
import com.cn.cms.utils.Page;
import com.cn.cms.web.ann.CheckAuth;
import com.cn.cms.web.ann.CheckToken;
import com.cn.cms.web.result.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/10/18 0018.
 */
@RestController
@RequestMapping(value = "/webapi/column/",produces = "application/json; charset=UTF-8")
@ResponseBody
public class ColumnController {

    @Autowired
    @Qualifier("columnService")
    private ColumnService columnService;

    @Resource
    private ColumnBiz columnBiz;

    /**
     * 频道列表 小说 新闻 视频等这种形式
     * @return
     */
    @CheckToken
    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public String list(){
        List<ColumnBean> list = columnBiz.listColumn() ;
        return ApiResponse.returnSuccess(list);
    }

    /**
     * 创建栏目
     * @param request
     * @retrun
     */


}