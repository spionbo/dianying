package com.cn.cms.po;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by Administrator on 2018/1/10 0010.
 * 小说分类
 */
@Setter
@Getter
public class XiaoshuoClassification extends Base{

    /*名称*/
    private String classifyName;

    /*分类 1，2，3，4*/
    private Integer classifyType;

}
