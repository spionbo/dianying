package com.cn.cms.logfactory;

import org.apache.commons.logging.LogFactory;

/**
 * Created by Administrator on 2017/11/3 0003.
 */
public class CommonLogFactory {
    public static CommonLog getLog(Class clazz){
        CommonLog commonLog = new CommonLog(LogFactory.getLog(clazz));
        return commonLog;
    }
}
