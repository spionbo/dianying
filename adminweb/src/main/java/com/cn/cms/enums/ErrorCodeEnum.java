package com.cn.cms.enums;

import lombok.Getter;

/**
 * Created by Administrator on 2017/10/19 0019.
 */
public enum ErrorCodeEnum {
    SUCCESS_CODE_DETAIL("SUCCESS_CODE_DETAIL", 0, "成功!"),
    ERROR_CODE_DEFAULT("ERROR_CODE_DEFAULT",-1,"天塌了，地陷了，小花猫不见了"),
    ERROR_LOGIN_FAIL("ERROR_LOGIN_FAIL",-110,"登录失败，请重新登录"),
    ERROR_NO_PERMISSION("ERROR_NO_PERMISSION",-111,"无权限"),
    ERROR_USERNAME_RE("ERROR_USERNAME_RE",-210,"用户名重复"),
    ERROR_COLUMN_ER("ERROR_COLUMN_ER",-310,"栏目名重复"),
    ERROR_USER_HASTRUE("ERROR_USER_HASTRUE",-410,"无此用名"),
    ERROR_USER_PWD("ERROR_USER_PWD",-510,"密码不一至"),
    ERROR_DELETE_COLUMN("ERROR_DELETE_COLUMN",-610,"栏目下面有子栏目，不能删除"),
    ERROR_UPDATE_COLUMN("ERROR_UPDATE_COLUMN",-710,"不能将栏目更新到自己的子栏目下");

    @Getter
    private int type;
    @Getter
    private String name;
    @Getter
    private String message;

    ErrorCodeEnum(String name,int type,String message){
        this.type = type;
        this.name = name;
        this.message = message;
    }

    public static ErrorCodeEnum get(int type){
        ErrorCodeEnum[] a = ErrorCodeEnum.values();
        for(int i=0; i<a.length; i++){
            ErrorCodeEnum b = a[i];
            if(b.getType() == type){
                return b;
            }
        }
        return ErrorCodeEnum.ERROR_CODE_DEFAULT;
    }
}
