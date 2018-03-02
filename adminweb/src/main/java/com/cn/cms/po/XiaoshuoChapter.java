package com.cn.cms.po;

import lombok.Getter;
import lombok.Setter;

/**
 * 小说章节
 * Created by Administrator on 2018/2/27 0027.
 */
@Getter
@Setter
public class XiaoshuoChapter extends XiaoshuoBase{

    /**
     * 标题
     */
    private String title;

    /**
     * 章节 为1，则为第一章的意思。
     */
    private Long num;

    /**
     * 父类id
     */
    private Long parentId;
}
