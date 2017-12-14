package com.cn.cms.web.controller;

import com.cn.cms.biz.PermissionBiz;
import com.cn.cms.bo.ColumnBean;
import com.cn.cms.bo.PermissionBean;
import com.cn.cms.enums.ErrorCodeEnum;
import com.cn.cms.po.Permission;
import com.cn.cms.utils.StringUtils;
import com.cn.cms.web.ann.CheckAuth;
import com.cn.cms.web.ann.CheckToken;
import com.cn.cms.web.result.ApiResponse;
import com.mchange.lang.IntegerUtils;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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

    /**
     * 新增后台栏目
     * @param request
     * @param name
     * @param parentId
     * @param url
     * @param sort
     * @param description
     * @return
     */
    @CheckToken
    @CheckAuth(name = "positionPermission:write")
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public String addPermissionColumn(HttpServletRequest request ,
                                      HttpServletResponse response,
                                      @RequestParam(value = "name") String name,
                                      @RequestParam(value = "parentId",required = false) Integer parentId,
                                      @RequestParam(value = "parentUrl",required = false) String parentUrl,
                                      @RequestParam(value = "url") String url,
                                      @RequestParam(value = "sort" , required = false) Integer sort,
                                      @RequestParam(value = "description" , required = false) String description){

        Integer a = permissionBiz.queryPermissionName(parentId , name);
        if(a>0){
            return ApiResponse.returnFail(ErrorCodeEnum.ERROR_COLUMN_ER.getType(),ErrorCodeEnum.ERROR_COLUMN_ER.getMessage());
        }
        String userID = getCurrentUserId(request);
        permissionBiz.savePermissionColumn(userID,name,parentId,parentUrl,url,sort,description);

        return ApiResponse.returnSuccess();
    }


}
