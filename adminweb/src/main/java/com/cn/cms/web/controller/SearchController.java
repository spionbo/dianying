package com.cn.cms.web.controller;

import com.cn.cms.middleware.RestSearchClient;
import com.cn.cms.middleware.bo.XiaoshuoSearch;
import com.cn.cms.web.ann.CheckToken;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.apache.http.HttpHost;
import javax.annotation.Resource;
import java.text.SimpleDateFormat;

/**
 * 搜索相关  搜索还没有做限制
 * Created by Administrator on 2018/3/1 0001.
 */
@Controller
@RequestMapping(value = "/webapi/search/",produces = "application/json; charset=UTF-8" )
@ResponseBody
public class SearchController extends BaseController {

   /* @Resource
    private RestSearchClient restSearchClient;*/

    /**
     * 全文检索小说
     * @param title
     * @param author
     * @param classifyId
     * @param startTime
     * @param endTime
     * @param status
     * @param id
     * @param setLastModifyUserId
     * @param lastModifyUserName
     * @param delTag
     * @param page
     * @param pageSize
     * @return
     */
    @CheckToken
    @RequestMapping(value = "/searchXiaoshuoChapter")
    public String searchChapter(
            @RequestParam(value = "title",required = false) String title,
            @RequestParam(value = "author",required = false) String author,
            @RequestParam(value = "classifyId",required = false) Integer classifyId,
            @RequestParam(value = "startTime", required = false) String startTime,
            @RequestParam(value = "endTime", required = false) String endTime,
            @RequestParam(value = "status", required = false) Boolean status,
            @RequestParam(value = "id", required = false) Long id,
            @RequestParam(value = "setLastModifyUserId", required = false) String setLastModifyUserId,
            @RequestParam(value = "lastModifyUserName", required = false) String lastModifyUserName,
            @RequestParam(value = "delTag", required = false, defaultValue = "1") Integer delTag,
            @RequestParam(value = "page", required = false) Integer page,
            @RequestParam(value = "pageSize", required = false) Integer pageSize
            ){

        /*RestHighLevelClient client = new RestHighLevelClient(RestClient.builder(
                new HttpHost("localhost", 9200, "http")));*/

        SimpleDateFormat sdf = new SimpleDateFormat();
        XiaoshuoSearch xiaoshuoSearch = new XiaoshuoSearch();
        xiaoshuoSearch.setTitle(title);
        xiaoshuoSearch.setAuthor(author);
        xiaoshuoSearch.setClassifyId(classifyId);
        xiaoshuoSearch.setLastModifyUserId(setLastModifyUserId);
        xiaoshuoSearch.setLastModifyUserName(lastModifyUserName);
        xiaoshuoSearch.setId(id);
        xiaoshuoSearch.setDelTag(delTag);
        //restSearchClient.searchXiaoshuo();
        return null;
    }
}
