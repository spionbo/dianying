package com.cn.cms.po;

import lombok.Getter;
import lombok.Setter;

import java.lang.reflect.Array;

/**
 * Created by Administrator on 2018/1/18 0018.
 */
@Setter
@Getter
public class UserPower extends Base{
    /**
     * 栏目ID
     */
    private Integer permissionId;

    /**
     * 用户ID
     */
    private String userId;

    /**
     * 拥有权限
     */
    private String power;
}
