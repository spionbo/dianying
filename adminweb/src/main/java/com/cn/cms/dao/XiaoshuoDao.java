package com.cn.cms.dao;

import com.cn.cms.po.XiaoshuoAbout;
import com.cn.cms.po.XiaoshuoChapter;
import com.cn.cms.po.XiaoshuoClassification;
import com.cn.cms.utils.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by Administrator on 2018/1/10 0010.
 */
public interface XiaoshuoDao {

    Integer getClassifyCount();

    void addClassify(@Param("name") String name);

    List<XiaoshuoClassification> getClassifyList(@Param(value="page") Page page);

    void updateClassify(@Param("p1") XiaoshuoClassification p1);

    List<XiaoshuoAbout> getXiaoshuoList(@Param("page") Page page);

    Integer getListCount();

    Integer getChapterCount(@Param("id") long id);

    List<XiaoshuoChapter> getXiaoshuoChapterList(@Param("page") Page page , @Param("id") long id);

    List<XiaoshuoChapter> getAllChapterList(@Param("page") Page page);
}
