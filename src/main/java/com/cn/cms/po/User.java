package com.cn.cms.po;

import com.cn.cms.utils.Hardware;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by Administrator on 2017/11/3 0003.
 */
@Getter
@Setter
public class User extends Base {
    /**
     * 用户名
     */
    private String userName;

    /**
     * 头像
     */
    private String headImage;

    /**
     * 真实名字
     */
    private String realName;

    /**
     * 密码串
     */
    private String pwd;

    /**
     * 用户ID 18位串
     */
   private String userId;

    /**
     * IDFA。如果IDFA为空，则使用MAC地址。
     */
    private String idfa;

    /*超级管理员身份*/
    private Boolean administrator = false;


}
