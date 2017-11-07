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


    //----自定义错误码

    public static final String ERROR_NO_POSITION = "找不到此用户组! ";

    public static final String ERROR_PWD = "密码错误! ";

    public static final String ERROR_NO_USER = "找不到此用户! ";

    public static final String ERROR_POSITION_RE = "用户组已经存在! ";

    public static final String ERROR_PERMISSION_NOT_FOUND = "找不到此权限! ";

    public static final String ERROR_FRAGMENT_NOT_FOUND = "找不到此碎片!";

    public static final String ERROR_FRAGMENT_LENGTH = "模版编辑参数不对称!";

    public static final String ERROR_FRAGMENT_MODEL = "碎片模版错误。";

    public static final String ERROR_TIME_PARSE = "时间格式不对，格式为：";

    public static final String ERROR_TEMPLATE_UPLOAD_OPEN = "文件上传-打开文件流失败!" ;

    public static final String ERROR_TEMPLATE_UPLOAD_WRITE = "文件上传-写入失败!" ;

    public static final String ERROR_TEMPLATE_DOWNLOAD_NOT_FOUND = "文件下载-找不到文件!";

    public static final String ERROR_TEMPLATE_DOWNLOAD_FILE_EX = "文件下载-文件读取异常!";

    public static final String ERROR_TEMPLATE_DOWNLOAD = "文件下载失败!";

    public static final String ERROR_DATE_PARSE = "时间格式不对.";

    public static final String ERROR_VIDEO = "视频上传失败!";

    public static final String ERROR_VIDEO_SIZE_0 = "视频不可以为0";


    public static final String ERROR_TEMPLATE_NOT_FOUND = "找不到频道、栏目对应的模版";

    public static final String ERROR_CHANNEL_NOT_FOUND = "找不到新闻对应的频道";

    public static final String ERROR_NEWS_NOT_FOUND = "找不到此篇新闻";

    public static final String ERROR_NEWS_LIST_NOT_FOUND = "没有新闻被选择";

    public static final String ERROR_NEWS_CAN_NOT_RESCIND = "当前新闻不在发布状态，无法撤销";

    public static final String ERROR_NEWS_NOT_NEED_RECOVER = "此新闻已被恢复";

    public static final String ERROR_TOPIC_NOT_FOUND = "找不到此模版";

    public static final String ERROR_TEMPLATE_PATH_FILENAME_DUP = "模版文件已存在，模版文件位置：";

    public static final String ERROR_CHANNEL_NAME_EXIST = "频道已经存在!";

    public static final String ERROR_COLUMN_NAME_EXIST = "栏目已经存在!";

    public static final String ERROR_NEWSCOLUMN_NOT_FOUND = "新闻栏目找不到!";

    public static final String ERROR_CATEGORY_NAME_EXIST = "部门已经存在!";

    public static final String ERROR_NOT_PUBLISH_NEWS = "用户没有发布权限!";

    public static final String ERROR_BASE_CODE = "文件不可以为空";

    public static final String ERROR_KEY_API = "无访问权限";

    public static final String ERROR_URL_ERROR = "无访问的方法";

    public static final String getTimeParseErrorYYYYMMDD(){
        return ERROR_TIME_PARSE.concat(YYYY_MM_DD);
    }

    //----自定义错误码

}
