package com.cn.cms.po;

import com.cn.cms.bo.ColumnPublishInfo;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by Administrator on 2017/12/8 0008.
 */
@Getter
@Setter
public class NovelColumn extends Base {
    /**
     * 栏目名称
     */
    private String columnName;

    /**
     * 频道ID
     */
    private Long channelId;

    /**
     * 父类id
     */
    private Integer parentId;

    /**
     * 相对路经
     */
    private String path;

    /**
     * 描述
     */
    private String description;

    /**
     * 额外赋值
     */
    private ColumnPublishInfo columnPublishInfo;

}
