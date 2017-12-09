package com.cn.cms.bo;

import com.cn.cms.po.Column;
import com.cn.cms.po.Permission;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;
import java.util.SortedMap;

/**
 * Created by Administrator on 2017/12/5 0005.
 */

@Setter
@Getter
public class ColumnBean {

    private Column column;

    private List<ColumnBean> columnBeans;

    /*[ //生成该页面
        {
            Permission : {},
            permissions : [
                {
                    Permission : "asdfsd",
                    permissions : [
                        {
                            Permission : "asdfsd",
                            permissions : []
                        }
                    ]
                }
            ]
        }
    ]*/

}
