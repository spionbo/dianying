package com.cn.cms.service.impl;

import com.cn.cms.biz.XiaoshuoBiz;
import com.cn.cms.dao.XiaoshuoDao;
import com.cn.cms.po.XiaoshuoAbout;
import com.cn.cms.po.XiaoshuoAuthor;
import com.cn.cms.po.XiaoshuoChapter;
import com.cn.cms.po.XiaoshuoClassification;
import com.cn.cms.service.XiaoshuoService;
import com.cn.cms.utils.Page;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Administrator on 2018/1/10 0010.
 */
@Repository
public class XiaoshuoServiceImpl implements XiaoshuoService {

    @Resource
    private XiaoshuoDao xiaoshuoDao;

    public void addClassify(String name){
        xiaoshuoDao.addClassify(name);
    };

    /**
     * 查看总数
     */
    public Integer getClassifyCount(){
        return xiaoshuoDao.getClassifyCount();
    };

    /**
     * 分类列表
     * @param page
     * @return
     */
    public List<XiaoshuoClassification> getClassifyList(Page page){
        return xiaoshuoDao.getClassifyList(page);
    };

    /**
     * 更新分类
     * @param classify
     */
    public void updateClassify(XiaoshuoClassification classify){
        xiaoshuoDao.updateClassify(classify);
    };

    public List<XiaoshuoAbout> getXiaoshuoList(Page page){
        return xiaoshuoDao.getXiaoshuoList(page);
    };

    public Integer getListCount(){
        return xiaoshuoDao.getListCount();
    }

    public Integer getChapterCount(long id){
        return xiaoshuoDao.getChapterCount(id);
    };

    public List<XiaoshuoChapter> getXiaoshuoChapterList(Page page , long id){
        return xiaoshuoDao.getXiaoshuoChapterList(page , id);
    }
    public List<XiaoshuoChapter> getAllChapterList( Page page){
        return xiaoshuoDao.getAllChapterList(page);
    }
    public void addAuthor(XiaoshuoAuthor xiaoshuoAuthor){
        xiaoshuoDao.addAuthor(xiaoshuoAuthor);
    };
}
