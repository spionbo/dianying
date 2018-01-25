package com.cn.cms.utils;

/**
 * Created by Administrator on 2018/1/22 0022.
 */
public class CommonUtil {

    /**
     * 首字母大写
     */
    public static String captureName(String name) {
        name = name.substring(0, 1).toUpperCase() + name.substring(1);
        return  name;

    }
}
