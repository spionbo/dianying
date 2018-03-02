package com.cn.cms.middleware.bo;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by Administrator on 2018/3/2 0002.
 */
@Getter
@Setter
public class XiaoshuoSearch {
    /**
     * 标题
     */
    private String title;

    /**
     * 作者
     */
    private String author;

    /**
     * 分类id
     */
    private Integer classifyId;

    /**
     * 来源
     */
    private String source;

    /**
     * 开始时间
     */
    private Long startTimeMillis;

    /**
     * 结束时间
     */
    private Long endTimeMillis;

    /**
     * ID
     */
    private Long id;

    /**
     * 修改人id
     */
    private String lastModifyUserId;

    /**
     * 修改人
     */
    private String lastModifyUserName;

    /**
     * 是否删除
     */
    private Integer delTag;
}
