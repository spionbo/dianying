package com.cn.cms.bo;

import com.cn.cms.po.XiaoshuoAbout;
import com.cn.cms.po.XiaoshuoChapter;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * Created by Administrator on 2018/3/15 0015.
 * 小说名称 about，及小说的列表 cehapters
 */
@Setter
@Getter
public class XiaoshuoBean {

    private XiaoshuoAbout xiaoshuoAbout;

    private List<XiaoshuoChapter> xiaoshuoChapters;
}
