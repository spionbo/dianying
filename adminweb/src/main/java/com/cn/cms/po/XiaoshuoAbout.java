package com.cn.cms.po;

import lombok.Getter;
import lombok.Setter;

/**
 * 文章说明 about
 * Created by Administrator on 2018/2/27 0027.
 */
@Getter
@Setter
public class XiaoshuoAbout extends XiaoshuoBase {

    /**
     * 父类id
     */
    private Long parentId;

    /**
     * 标题
     */
    private String title;

    /**
     *状态 0完结，1正在更新 2断更
     */
    private Integer status;

    /**
     * 作者
     */
    private String author;

    /**
     * 作者约束id
     */
    private Long authorId;

    /**
     * 简介
     */
    private String dec;

    /**
     * 排序
     */
    private Long sort;

    /**
     * 多少字
     */
    private Long count;
}
