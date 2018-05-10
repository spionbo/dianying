package com.cn.cms.web.controller;

import com.cn.cms.biz.PermissionBiz;
import com.cn.cms.biz.UserPowerBiz;
import com.cn.cms.bo.PermissionBean;
import com.cn.cms.contants.PermissionNames;
import com.cn.cms.enums.ErrorCodeEnum;
import com.cn.cms.enums.PlatformEnum;
import com.cn.cms.po.Permission;
import com.cn.cms.web.ann.CheckAuth;
import com.cn.cms.web.ann.CheckToken;
import com.cn.cms.web.result.ApiResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

/**
 * Created by Administrator on 2017/11/30 0030.
 */
@Controller
@RequestMapping(value = "/webapi/permission/", produces = "application/json;charset=UTF-8")
@ResponseBody
public class PermissionController extends BaseController{

    @Resource
    private PermissionBiz permissionBiz;

    @Resource
    private UserPowerBiz userPowerBiz;

    /**
     * 获取所有栏目
     * @return
     */
    @CheckToken
    @CheckAuth(name = PermissionNames.BACKSTAGE.READ)
    @RequestMapping(value = "/allColumn",method = RequestMethod.GET)
    public String allColumn(HttpServletRequest request,
                            @RequestParam(name = "isTree",required = false) Boolean isTree){
        String userId = getCurrentUserId(request);
        if(isTree){ //对像树状
            return ApiResponse.returnSuccess(permissionBiz.getAllColumnBean(userId,PlatformEnum.CMS));
        }
        //1维数组
        return ApiResponse.returnSuccess(permissionBiz.getAllColumn(userId,PlatformEnum.CMS));
    }

    /**
     * 获取用户拥有的菜单栏权限
     * @param request
     * @return
     */
    @CheckToken
    @CheckAuth(name=PermissionNames.BACKSTAGE.READ)
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
    @CheckAuth(name = PermissionNames.BACKSTAGE.COLUMN.WRITE)
    @RequestMapping(value = "/addColumn",method = RequestMethod.POST)
    public String addPermissionColumn(HttpServletRequest request ,
                                      HttpServletResponse response,
                                      @RequestParam(value = "name") String name,
                                      @RequestParam(value = "parentId",required = false) Integer parentId,
                                      @RequestParam(value = "url") String url,
                                      @RequestParam(value = "sort" , required = false) Integer sort,
                                      @RequestParam(value = "description" , required = false) String description){

        Integer a = permissionBiz.queryPermissionName(parentId , name);
        if(a>0){
            return ApiResponse.returnFail(ErrorCodeEnum.ERROR_COLUMN_ER.getType(),ErrorCodeEnum.ERROR_COLUMN_ER.getMessage());
        }
        String userID = getCurrentUserId(request);
        permissionBiz.savePermissionColumn(userID,name,parentId,url,sort,description, PlatformEnum.CMS);
        return ApiResponse.returnSuccess();
    }

    /**
     * 删除栏目，如果该栏目有子栏目，则删除失败。
     * @param request
     * @param permissionId
     * @return
     */
    @CheckToken
    @CheckAuth(name = PermissionNames.BACKSTAGE.COLUMN.DELETE)
    @RequestMapping(value = "/deleteColumn",method = RequestMethod.POST)
    public String deletePermissionColumn(HttpServletRequest request,
                                         @RequestParam(value = "columnId") Integer permissionId){
        String userId = getCurrentUserId(request);
        if(!permissionBiz.hasPermission(permissionId , userId)){
            userPowerBiz.deleteUserPower(false,permissionId);
            return ApiResponse.returnSuccess();
        }
        return ApiResponse.returnFail(ErrorCodeEnum.ERROR_DELETE_COLUMN.getType(),ErrorCodeEnum.ERROR_DELETE_COLUMN.getMessage());
    }

    /**
     * 更新栏目
     * @param request
     * @param response
     * @param id
     * @param name
     * @param parentId
     * @param url
     * @param sort
     * @param description
     * @return
     */
    @CheckToken
    @CheckAuth(name = PermissionNames.BACKSTAGE.COLUMN.UPDATE)
    @RequestMapping(value = "/updateColumn",method = RequestMethod.POST)
    public String updatePermissionColumn(HttpServletRequest request ,
                                         HttpServletResponse response,
                                         @RequestParam(value = "id") Integer id,
                                         @RequestParam(value = "name") String name,
                                         @RequestParam(value = "parentId",required = false) Integer parentId,
                                         @RequestParam(value = "url") String url,
                                         @RequestParam(value = "sort" , required = false) Integer sort,
                                         @RequestParam(value = "description" , required = false) String description){
        String userID = getCurrentUserId(request);
        if(parentId!=null){
            if(permissionBiz.hasAddToChild(id , parentId, userID)){
                return ApiResponse.returnFail(ErrorCodeEnum.ERROR_UPDATE_COLUMN.getType(),ErrorCodeEnum.ERROR_UPDATE_COLUMN.getMessage());
            }
        }
        permissionBiz.updatePermissionColumn(id,userID,name,parentId,url,sort,description);
        return ApiResponse.returnSuccess();
    }


    /**
     * 获取后台栏目信息
     * @param request
     * @param columnId
     * @return
     */
    @CheckToken
    @CheckAuth(name = PermissionNames.BACKSTAGE.COLUMN.READ)
    @RequestMapping(value = "/permissionColumn", method = RequestMethod.GET)
    public String permissionColumn(HttpServletRequest request,
                                   @RequestParam(value = "columnId") Integer columnId){
        Permission permission = permissionBiz.getPermissionColumn(columnId);
        return ApiResponse.returnSuccess(permission);
    }
}
