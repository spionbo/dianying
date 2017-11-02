package com.cn.cms.contants;

/**
 * Created by Administrator on 2017/11/1 0001.
 */
public class RedisKeyContants {

    /*token*/
    public static final String REDIS_TOKEN_KEY = "CMS_TOKEN_KEY_";

    public static final String REDIS_APP_TOKEN_KEY = "CMS_APP_TOKEN_KEY_";

    /*key*/
    public static final String REDIS_CMS_POSITION_PERMISSION_KEY="CMS_CMS_PERMISSION_USERID_";

    /*用户信息*/
    public static String getCMSPermission(String userId){
        return REDIS_CMS_POSITION_PERMISSION_KEY + userId;
    }

}
