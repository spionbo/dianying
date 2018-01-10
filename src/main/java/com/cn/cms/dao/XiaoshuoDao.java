package com.cn.cms.dao;

import org.apache.ibatis.annotations.Param;

/**
 * Created by Administrator on 2018/1/10 0010.
 */
public interface XiaoshuoDao {

    public void saveClassify(@Param("name") String name);
}
