package com.cn.cms.dao;

import com.cn.cms.po.XiaoshuoClassification;
import com.cn.cms.utils.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by Administrator on 2018/1/10 0010.
 */
public interface XiaoshuoDao {

    Integer queryClassifyCount();

    void addClassify(@Param("name") String name);

    List<XiaoshuoClassification> queryClassifyList(@Param(value="page") Page page);

    void updateClassify(@Param("p1") XiaoshuoClassification p1);
}
