package com.cn.cms.utils;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

/**
 * Created by Administrator on 2017/12/21 0021.
 */
public class PropertyManager {
    /**
     * 读取指定为rb.properties文件
     * @param rb property 文件，文件名为 rb.properties
     * @param key
     * @return value
     */
    public static String getRB(String rb, String key){
        try {
            String val = ResourceBundle.getBundle(rb).getString(key);
            return val;
        } catch (MissingResourceException e) {
            throw new RuntimeException( '!' + rb + ":" + key + '!');
        }
    }
}
