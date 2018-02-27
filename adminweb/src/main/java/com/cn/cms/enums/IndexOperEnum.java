package com.cn.cms.enums;

import lombok.Getter;

/**
 *  定时生成或者触发生成枚举
 * Created by Administrator on 2017/12/25 0025.
 */
public enum IndexOperEnum {
    update("update", 1),
    delete("delete", 2);

    @Getter
    private int type;
    @Getter
    private String name;

    IndexOperEnum(String name, int type){
        this.type = type;
        this.name = name;
    }

    public static IndexOperEnum get(int type){
        IndexOperEnum[] a = IndexOperEnum.values();
        for(int i=0; i<a.length; i++){
            IndexOperEnum b = a[i];
            if(b.getType() == type){
                return b;
            }
        }
        return null;
    }
}
