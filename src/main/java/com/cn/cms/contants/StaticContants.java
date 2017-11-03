package com.cn.cms.contants;

/**
 * Created by Administrator on 2017/10/19 0019.
 */

import com.alibaba.fastjson.serializer.SerializeConfig;

import java.util.HashMap;
import java.util.Map;

/**
 * 所有静态类的变量定义。都在这个类里
 * Created by ADMIN on 16/11/15.
 */
public class StaticContants {

    public static final String HEADER_CONTENT_TYPE = "Content-Type";

    public static final String JSON_UTF8 = "application/json;charset=UTF-8";

    public static final String DOWN_LOAD_CONTENT_TYPE = "multipart/form-data";

    public static final String HEADER_CONTENT_DISPOSITION = "Content-Disposition";

    public static final String HEADER_ATTACHMENT = "attachment;fileName=";

    public static String getHEADER_ATTACHMENT(String fileName){
        return  HEADER_ATTACHMENT.concat(fileName);
    }

    public static final String UTF8 = "UTF-8";

    //---默认分页数  start
    public static final int PAGE_SIZE = 20 ;
    public static final int PAGE = 1 ;

    //---默认分页书  end

    public static final String LOGO_NAME = "/logo.png";

    public static final String COOKIE_USER_ID = "CMS_QUANJING_USER_ID";

    public static final String COOKIE_USER_TOKEN = "CMS_QUANJING_USER_TOKEN";

    public static final String COOKIE_TIME = "CMS_QUANJING_TIME";

    public static final String COOKIE_USER_KEY = "CMS_QUANJING_USER_KEY";

    public static final String COOKIE_REAL_NAME = "CMS_QUANJING_NAME";

    /**
     * 资源文件名前缀
     */
    public static final String QUANJING_RESOURCE_FILENAME = "QJ_";

    public static final String FILE_PATH_SP = "/";

    public static final String CMS_OPERATION_USER_ID = "100000000000000001";

    public static final String CMS_SPIDER_USER_ID = "100000000000000010";

    public static final Map<String, String> CMSMAP = new HashMap<>();

    static {
        CMSMAP.put(CMS_OPERATION_USER_ID, CMS_OPERATION_USER_ID);
        CMSMAP.put(CMS_SPIDER_USER_ID, CMS_SPIDER_USER_ID);
    }

    public static final String CMS_INSIDE_USER_KEY = "CMS_INSIDE_USER_KEY";

    public static final int CMS_NUM = 100;

    public static final String FRAGMENT_REGEX = "\\{#([^#][^\\}]+)#\\}";

    public static final String FRAGMENT_ALL_REGEX = "(.*?)";


    public static final String YYYY_MM_DD = "yyyy-MM-dd";

    public static final String YYYY_MM_DD_HH_MM = "yyyy-MM-dd HH:mm";

    public static final String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";


    public static final String UNDER_LINE = "_";

    public static final String CONNECT_LINE = "-";


    public static final String ESSEARCH_JOB_LIST = "ESSEARCH_JOB_LIST_KEY";

    public static final SerializeConfig config = new SerializeConfig();


}
