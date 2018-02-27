package com.cn.cms.dao;

import com.cn.cms.po.Column;
import com.cn.cms.po.SecondColumn;
import com.cn.cms.utils.Page;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created by Administrator on 2017/10/18 0018.
 */
public interface ColumnDao {

    Integer queryColumnCount(@Param(value = "userId") String userId, @Param(value = "publish") Integer publish, @Param(value = "delTag") Integer delTag);

    List<Column> queryColumnList(@Param(value="userId") String userId,
                                 @Param(value="publish") Integer publish,
                                 @Param(value = "delTag") Integer delTag);


}
