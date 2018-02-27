package com.cn.cms.utils;

import java.util.Collection;

/**
 * Created by Administrator on 2017/11/3 0003.
 */
public class StringUtils extends org.apache.commons.lang3.StringUtils{

    /**
     * 截取字符
     * @param content
     * @param length
     * @return
     */
    public static String sub(String content, int length){
        if(StringUtils.isNotBlank(content) && content.length() > length){
            return content.substring(0, length);
        }
        return content;
    }

    /**
     * 删除首个prefix
     * @param content
     * @param prefix
     * @return
     */
    public static String delFirstPrefix(String content, String prefix){
        while(isNotBlank(content) && isNotBlank(prefix) && content.startsWith(prefix)){
            content = content.substring(prefix.length());
        }
        return content;
    }

    /**
     * 拼接URL数据
     * @param strings
     * @return
     */
    public static String concatUrl(String... strings){
        StringBuffer sbf = new StringBuffer();
        if(strings!=null && strings.length>0){
            for( int i = 0 ; i < strings.length ; i ++){
                if(isNotBlank(strings[i])) {
                    if (i == 0) {
                        if (strings[i].endsWith("/")) {
                            sbf.append(strings[i]);
                        } else {
                            sbf.append(strings[i]);
                            sbf.append("/");
                        }
                    } else if (i + 1 == strings.length) {
                        if (strings[i].startsWith("/")) {
                            sbf.append(strings[i].substring(1));
                        } else {
                            sbf.append(strings[i]);
                        }
                    } else {
                        if (strings[i].startsWith("/")) {
                            if (strings[i].endsWith("/")) {
                                sbf.append(strings[i].substring(1));
                            } else {
                                sbf.append(strings[i].substring(1));
                                sbf.append("/");
                            }
                        } else {
                            if (strings[i].endsWith("/")) {
                                sbf.append(strings[i]);
                            } else {
                                sbf.append(strings[i]);
                                sbf.append("/");
                            }
                        }
                    }
                }
            }
        }
        return sbf.toString();
    }

    /**
     * 检测列表是否不为空
     * @param collection
     * @return
     */
    public static boolean isNotEmpty(Collection<? extends Object> collection){
        if(collection!=null && collection.size()>0){
            return true;
        }
        return false;
    }

    /**
     * 检测列表是否为空
     * @param collection
     * @return
     */
    public static boolean isEmpty(Collection<? extends Object> collection){
        if(collection == null || collection.size() == 0){
            return true;
        }
        return false;
    }

    /**
     *
     * 转义字符。json转义双引号
     * 转义字符。js转义单引号
     * 转义字符。xml转义&符号
     * @param str
     * @param format 支持JSON，XML，js
     * @return
     */
    public static String filter(String str, String format){
        String result = "";
        if(isNotBlank(str)) {
            switch (format) {
                case "json":
                case "JSON": {
                    str = str.replaceAll("\\\\\"", "\"");
                    result = str.replaceAll("\"", "\\\\\"");
                    break;
                }
                case "xml":
                case "XML": {
                    str = str.replaceAll("&amp;", "&");
                    result = str.replaceAll("&", "&amp;");
                    break;
                }
                case "js":
                case "JS": {
                    str = str.replaceAll("\\\\'", "'");
                    result = str.replaceAll("'", "\\\\'");
                    break;
                }
                default: {
                    result = str;
                    break;
                }
            }
        }
        return result;
    }


    public static void main(String[] args){
        System.out.println(concatUrl("http://www.baidu.com","/mmindex.html"));
        System.out.println(filter("", "XML"));
    }
}
