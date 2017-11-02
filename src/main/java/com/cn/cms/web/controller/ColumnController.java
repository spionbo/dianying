package com.cn.cms.web.controller;

import com.cn.cms.po.Column;
import com.cn.cms.po.SecondColumn;
import com.cn.cms.service.ColumnService;
import com.cn.cms.web.result.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by Administrator on 2017/10/18 0018.
 */
@RestController
@RequestMapping(value = "/web/column/",produces = "application/json; charset=UTF-8")
@ResponseBody
public class ColumnController {

    @Autowired
    @Qualifier("columnService")
    private ColumnService columnService;

    /*一级栏目*/
    @RequestMapping(value = "menu",method = RequestMethod.GET)
    public String index(HttpServletRequest request){
        //String userID = getCurrentUserId(request);获取权限
        List<Column> columnList = columnService.getColumn();
        return ApiResponse.returnSuccess(columnList);
    }

    /*二级栏目*/
    @RequestMapping(value = "second_column",method = RequestMethod.GET)
    public String secondColumn( Integer id ){
        List<SecondColumn> second_columns = columnService.getSecondColumn(id);
        return ApiResponse.returnSuccess(second_columns);
    }

    /*获取左则栏目列表*/
    /*@RequestMapping(value = "currentMenu",method = RequestMethod.GET)
    public String currentMenu(Integer id){

    }*/

    /*所有栏目列表*/
    @RequestMapping(value = "/currentMenuPermission",method = RequestMethod.GET)
    public String currentMenuPermission(){
        return ApiResponse.returnSuccess();
    }

}