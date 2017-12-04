package com.cn.cms.dao;

import com.cn.cms.po.Column;
import com.cn.cms.po.SecondColumn;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created by Administrator on 2017/10/18 0018.
 */
public interface ColumnDao {

    /*一级栏目*/
    @Select("SELECT id,name FROM `column`")
    List<Column> selectColumn();

    /*二级栏目*/
    @Select("select id,name from second_column where column_id=#{id}")
    List<SecondColumn> selectSecondColumnById(@Param("id") Integer id);



}
