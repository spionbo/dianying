package com.cn.cms.service;

import com.cn.cms.po.XiaoshuoAbout;
import com.cn.cms.po.XiaoshuoChapter;
import com.cn.cms.po.XiaoshuoClassification;
import com.cn.cms.utils.Page;

import java.util.List;

/**
 * Created by Administrator on 2018/1/10 0010.
 */
public interface XiaoshuoService {
    /**
     * 新增分类
     * @param name
     */
    void addClassify(String name);

    /**
     * 查看总数
     */
    Integer getClassifyCount();

    /**
     * 分类列表
     * @param page
     * @return
     */
    List<XiaoshuoClassification> getClassifyList(Page page);

    /**
     * 更新分类
     * @param classify
     */
    void updateClassify(XiaoshuoClassification classify);

    /**
     * 获取文章列表
     */
    List<XiaoshuoAbout> getXiaoshuoList(Page page);

    /**
     * 获取列表总数
     * @return
     */
    Integer getListCount();

    /**
     * 获取章节总数
     * @return
     */
    Integer getChapterCount(long id);

    /**
     * 获取章节列表
     * @param page
     * @return
     */
    List<XiaoshuoChapter> getXiaoshuoChapterList(Page page , long id);

    /**
     * 获取所有章节
     * @return
     */
    List<XiaoshuoChapter> getAllChapterList(Page page);
}
