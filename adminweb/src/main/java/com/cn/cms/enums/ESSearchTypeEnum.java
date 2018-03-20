package com.cn.cms.enums;

import lombok.Getter;

/**
 * Created by Administrator on 2017/12/25 0025.
 */
public enum ESSearchTypeEnum {
    xiaoshuoAbout("xiaoshuoabout", "cmsxiaoshuo_about_index", 1),
    xiaoshuoChapter("xiaoshuochapter","cmsxiaoshuo_chapter_index", 2);

    @Getter
    private int type;
    @Getter
    private String name;
    @Getter
    private String index;

    ESSearchTypeEnum(String name, String index,int type){
        this.type = type;
        this.name = name;
        this.index = index;
    }

    public static ESSearchTypeEnum get(int type){
        ESSearchTypeEnum[] a = ESSearchTypeEnum.values();
        for(int i=0; i<a.length; i++){
            ESSearchTypeEnum b = a[i];
            if(b.getType() == type){
                return b;
            }
        }
        return null;
    }
}
