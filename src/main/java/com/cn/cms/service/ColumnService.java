package com.cn.cms.service;

import com.cn.cms.po.Column;
import com.cn.cms.po.SecondColumn;

import java.util.List;

/**
 * Created by Administrator on 2017/10/18 0018.
 */
public interface ColumnService {

    List<Column> getColumn();

    List<SecondColumn> getSecondColumn(Integer id);

}
