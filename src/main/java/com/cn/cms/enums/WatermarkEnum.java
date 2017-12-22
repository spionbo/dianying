package com.cn.cms.enums;

import lombok.Getter;

/**
 * Created by Administrator on 2017/12/21 0021.
 */
public enum WatermarkEnum {
    watermark("水印", 1),
    notwatermark("不打水印", 0);

    @Getter
    private int type;
    @Getter
    private String name;

    WatermarkEnum(String name, int type){
        this.type = type;
        this.name = name;
    }

    public static WatermarkEnum get(int type){
        WatermarkEnum[] a = WatermarkEnum.values();
        for(int i=0; i<a.length; i++){
            WatermarkEnum b = a[i];
            if(b.getType() == type){
                return b;
            }
        }
        return WatermarkEnum.notwatermark;
    }
}
