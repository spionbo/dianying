package com.cn.cms.po;

import com.cn.cms.bo.ColumnPublishInfo;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by Administrator on 2017/12/8 0008.
 */
@Setter
@Getter
public class Column extends Base {

    /**
     * 栏目名称
     */
    private String columnName;

    /**
     * 栏目ID
     */
    private Integer columnId;

    /**
     * 父类id
     */
    private Integer parentId;

    /**
     * 相对路经
     */
    private String path;



}
