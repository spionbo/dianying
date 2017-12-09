package com.cn.cms.web.controller;

import com.cn.cms.biz.PermissionBiz;
import com.cn.cms.bo.ColumnBean;
import com.cn.cms.bo.PermissionBean;
import com.cn.cms.po.Permission;
import com.cn.cms.utils.StringUtils;
import com.cn.cms.web.ann.CheckAuth;
import com.cn.cms.web.ann.CheckToken;
import com.cn.cms.web.result.ApiResponse;
import com.mchange.lang.IntegerUtils;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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

    /**
     * 新增后台栏目
     */
    @CheckToken
    @CheckAuth(name = "positionpermission:write")
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public String addPermissionColumn(HttpServletRequest request ,
                                      @RequestParam(value = "name") String name,
                                      @RequestParam(value = "parentId") Integer parentId,
                                      @RequestParam(value = "url") String url,
                                      @RequestParam(value = "sort") Integer sort,
                                      @RequestParam(value = "description") String description){
        String userId = getCurrentUserId(request);
        Permission permission = new Permission();
        permission.setName(name);
        permission.setUrl(url);
        permission.setLastModifyUserId(getCurrentUserId(request));
        permission.setCreateUserId(getCurrentUserId(request));
        if(StringUtils.isNotBlank(url)){
            permission.setUrl(url);
        }
        if(StringUtils.isNotBlank(description)){
            permission.setDescription(description);
        }
        if(parentId!=null){
            permission.setParentId(parentId);
        }
        if(sort!=null){
            permission.setSort(sort);
        }
        permissionBiz.savePermissionColumn(permission);

        return ApiResponse.returnSuccess();
    }

}
