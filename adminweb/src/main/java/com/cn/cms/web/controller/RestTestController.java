package com.cn.cms.web.controller;

import com.cn.cms.biz.XiaoshuoBiz;
import com.cn.cms.bo.XiaoshuoBean;
import com.cn.cms.middleware.RestSearchClient;
import com.cn.cms.po.XiaoshuoAbout;
import com.cn.cms.po.XiaoshuoChapter;
import com.cn.cms.utils.Page;
import com.cn.cms.utils.StringUtils;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

/**Elasticsearch
 * Created by Administrator on 2018/3/15 0015.
 */
@Controller
@RequestMapping(value = "/webapi/test/",produces = "application/json;charset=UTF-8")
@ResponseBody
public class RestTestController extends BaseController {

    private final static int PAGE_SIZE = 1000;

    @Resource
    private XiaoshuoBiz xiaoshuoBiz;

    @Resource(name = "threadTaskExecutor")
    private ThreadPoolTaskExecutor threadPoolTaskExecutor;

    @Resource
    private RestSearchClient restSearchClient;

    @RequestMapping(value = "/updateXiaoshuoList",method = RequestMethod.POST)
    public String updateXiaoshuoList(@RequestParam(value = "p",required = false) final Integer p){
        Page page = new Page();
        page.setPage(0);
        page.setCount(p);
        if (p >= PAGE_SIZE) {
            page.setPageSize(PAGE_SIZE);
        } else {
            page.setPageSize(p);
        }

        Runnable runnable = ()->{
            do{
                page.setPage(page.getPage()+1);
                log.info("开始执行查询操作..当前页：{" + page.getPage() + "},总条数：{" + page.getCount() + "}.");

                List<XiaoshuoAbout> list = xiaoshuoBiz.getList(page);
                if(StringUtils.isNotEmpty(list)){
                    for (XiaoshuoAbout about : list){
                        restSearchClient.updateXiaoshuoAbout(about);
                    }
                    log.info("执行更新索引完毕.当前页：{" + page.getPage() + "},总条数：{" + page.getCount() + "}.");
                } else {
                    log.info("查询结果为空，结束本次更新.当前页：{" + page.getPage() + "},总条数：{" + page.getCount() + "}.");
                    break;
                }

            }while (page.hasNextPage());
            log.info("完成任务");
        };
        threadPoolTaskExecutor.execute(runnable);

        return null;
    }

    @RequestMapping(value = "/updateXiaoshuoChapter",method = RequestMethod.POST)
    public String updateXiaoshuoChapter(@RequestParam(value = "p",required = false) final Integer p){
        Page page = new Page();
        page.setPage(0);
        page.setCount(p);
        if (p >= PAGE_SIZE) {
            page.setPageSize(PAGE_SIZE);
        } else {
            page.setPageSize(p);
        }

        Runnable runnable = ()->{
            do{
                page.setPage(page.getPage()+1);
                log.info("开始执行查询操作..当前页：{" + page.getPage() + "},总条数：{" + page.getCount() + "}.");

                List<XiaoshuoChapter> list = xiaoshuoBiz.getAllChapterList(page);
                if(StringUtils.isNotEmpty(list)){
                    for (XiaoshuoChapter chapter : list){
                        restSearchClient.updateXiaoshuoChapter(chapter);
                    }
                    log.info("执行更新索引完毕.当前页：{" + page.getPage() + "},总条数：{" + page.getCount() + "}.");
                } else {
                    log.info("查询结果为空，结束本次更新.当前页：{" + page.getPage() + "},总条数：{" + page.getCount() + "}.");
                    break;
                }

            }while (page.hasNextPage());
            log.info("完成任务");
        };
        threadPoolTaskExecutor.execute(runnable);

        return null;
    }
}
