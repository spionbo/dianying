package com.cn.cms.web.controller;

import com.cn.cms.biz.XiaoshuoBiz;
import com.cn.cms.bo.PermissionBean;
import com.cn.cms.contants.PermissionNames;
import com.cn.cms.po.*;
import com.cn.cms.utils.Page;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    @CheckAuth(name = PermissionNames.XIAOSHUO.ADD_CLASS.WRITE)
    @RequestMapping(value = "/addClassify",method = RequestMethod.POST)
    public String addClassify(HttpServletRequest request,
                              @RequestParam(value = "name") String name){
        xiaoshuoBiz.addClassify(name);
        return ApiResponse.returnSuccess();
    }

    /**
     * 查看分类列表
     * @param request
     * @param page
     * @param pageSize
     * @return
     */
    @CheckToken
    @CheckAuth(name=PermissionNames.XIAOSHUO.CLASS_LIST.READ)
    @RequestMapping(value = "/classifyList",method = RequestMethod.GET)
    public String classifyList(HttpServletRequest request,
                               @RequestParam(value = "page",required = false) Integer page,
                               @RequestParam(value="pageSize",required = false)Integer pageSize){

        Page pageObj = new Page(page,pageSize);
        List<XiaoshuoClassification> xiaoshuoClassifications = xiaoshuoBiz.queryClassifyList(pageObj);
        Map<String, Object> result = new HashMap<>();
        result.put("page",pageObj);
        result.put("list",xiaoshuoClassifications);

        return ApiResponse.returnSuccess(result);
    }

    /**
     * 更新分类 只有列表有该功能,则权限为列表
     * @param request
     * @param id
     * @param classifyName
     * @return
     */
    @CheckToken
    @CheckAuth(name=PermissionNames.XIAOSHUO.CLASS_LIST.UPDATE)
    @RequestMapping(value = "/updateClassify",method = RequestMethod.POST)
    public String updateClassify(HttpServletRequest request,
                                 @RequestParam(value = "id") Long id ,
                                 @RequestParam(value = "classifyName") String classifyName){
        String userID = getCurrentUserId(request);
        XiaoshuoClassification classify = new XiaoshuoClassification();
        classify.setLastModifyUserId(userID);
        classify.setClassifyName(classifyName);
        classify.setId(id);
        xiaoshuoBiz.updateClassify(classify);
        return ApiResponse.returnSuccess();
    }

    /**
     * 查看小说列表
     * @param request
     * @param page
     * @param pageSize
     * @return
     */
    @CheckToken
    @CheckAuth(name = PermissionNames.XIAOSHUO.LIST.READ)
    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public String getList(HttpServletRequest request,
                          @RequestParam(value = "page",required = false) Integer page,
                          @RequestParam(value="pageSize",required = false)Integer pageSize){
        Page pageObj = new Page(page,pageSize);
        List<XiaoshuoAbout> xiaoshuoAbouts = xiaoshuoBiz.getList(pageObj);
        Map<String, Object> result = new HashMap<>();
        result.put("page",pageObj);
        result.put("list",xiaoshuoAbouts);
        return ApiResponse.returnSuccess(result);
    }

    /**
     * 获取章节列表
     * @param request
     * @param page
     * @param pageSize
     * @return
     */
    @CheckToken
    @CheckAuth(name = PermissionNames.XIAOSHUO.LIST.READ)
    @RequestMapping(value = "/chapterList",method = RequestMethod.GET)
    public String chapterList(HttpServletRequest request,
                              @RequestParam(value = "id") long id,
                              @RequestParam(value = "page",required = false) Integer page,
                              @RequestParam(value="pageSize",required = false)Integer pageSize) {
        Page pageObj = new Page(page,pageSize);
        List<XiaoshuoChapter> xiaoshuoChapters = xiaoshuoBiz.getChapterList(pageObj , id);
        Map<String, Object> result = new HashMap<>();
        result.put("page",pageObj);
        result.put("list",xiaoshuoChapters);
        return ApiResponse.returnSuccess(result);
    }
    
    /**
     * 新增作者
     * @param request
     * @param name
     * @param alas
     * @param birh
     * @param addess
     * @param dateOfBirth
     * @param description
     * @return
     */
    @CheckToken
    @CheckAuth(name = PermissionNames.XIAOSHUO.ADD_AUTHOR.WRITE)
    @RequestMapping(value = "/addAuthor",method = RequestMethod.GET)
    public String addAuthor(HttpServletRequest request,
                            @RequestParam(value = "name" , required = false) String name,
                            @RequestParam(value = "alas") String alas,
                            @RequestParam(value = "birh" , required = false) String birh,
                            @RequestParam(value = "addess" , required = false) String addess,
                            @RequestParam(value = "dateOfBirth" , required = false) String dateOfBirth,
                            @RequestParam(value = "description" , required = false) String description){
        xiaoshuoBiz.addAuthor(name,alas,birh,addess,dateOfBirth,description);
        return ApiResponse.returnSuccess();
    }

    /**
     * 修改作者
     * @param request
     * @param name
     * @param alas
     * @param birh
     * @param addess
     * @param dateOfBirth
     * @param description
     * @return
     */
    @CheckToken
    @CheckAuth(name = PermissionNames.XIAOSHUO.AUTHOR_LIST.UPDATE)
    @RequestMapping(value="/updateAuthor",method = RequestMethod.POST)
    public String updateAuthor(HttpServletRequest request,
                               @RequestParam(value = "name" , required = false) String name,
                               @RequestParam(value = "alas") String alas,
                               @RequestParam(value = "birh" , required = false) String birh,
                               @RequestParam(value = "addess" , required = false) String addess,
                               @RequestParam(value = "dateOfBirth" , required = false) String dateOfBirth,
                               @RequestParam(value = "description" , required = false) String description){
        xiaoshuoBiz.updateAuthor(name,alas,birh,addess,dateOfBirth,description);
        return ApiResponse.returnSuccess();
    }

    /**
     * 删除作者
     * @param request
     * @param id
     * @return
     */
    @CheckToken
    @CheckAuth(name = PermissionNames.XIAOSHUO.AUTHOR_LIST.DELETE)
    @RequestMapping(value = "/deleteAuthor",method = RequestMethod.POST)
    public String deleteAuthor(HttpServletRequest request,
                               @RequestParam("id") long id){
        xiaoshuoBiz.deleteAuthor(id);
        return ApiResponse.returnSuccess();
    }

    /**
     * 获取小说作者列表
     * @param request
     * @param page
     * @param pageSize
     * @return
     */
    @CheckToken
    @CheckAuth(name = PermissionNames.XIAOSHUO.AUTHOR_LIST.READ)
    @RequestMapping(value = "/getAuthorList",method = RequestMethod.GET)
    public String getAuthorList(HttpServletRequest request,
                                @RequestParam(value = "page",required = false) Integer page,
                                @RequestParam(value="pageSize",required = false)Integer pageSize){
        Page pageObj = new Page(page,pageSize);
        List<XiaoshuoAuthor> authors = xiaoshuoBiz.getAuthorList(pageObj);
        Map<String, Object> result = new HashMap<>();
        result.put("page",pageObj);
        result.put("list",authors);
        return ApiResponse.returnSuccess(result);
    }

}
