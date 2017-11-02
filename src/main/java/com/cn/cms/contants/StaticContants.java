package com.cn.cms.contants;

/**
 * Created by Administrator on 2017/10/19 0019.
 */

import com.alibaba.fastjson.serializer.SerializeConfig;

/**
 * 所有静态类的变量定义。都在这个类里
 * Created by ADMIN on 16/11/15.
 */
public class StaticContants {

    public static final String HEADER_CONTENT_TYPE = "Content-Type";
    public static final String JSON_UTF8 = "application/json;charset=UTF-8";
    public static final String DOWN_LOAD_CONTENT_TYPE = "multipart/form-data";
    public static final String HEADER_CONTENT_DISPOSITION = "Content-Disposition";

    public static final SerializeConfig config = new SerializeConfig();


    public static final String YYYY_MM_DD = "yyyy-MM-dd";

    public static final String YYYY_MM_DD_HH_MM = "yyyy-MM-dd HH:mm";

    public static final String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";

}
