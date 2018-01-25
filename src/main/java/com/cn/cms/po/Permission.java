package com.cn.cms.po;

import com.cn.cms.enums.PlatformEnum;
import com.cn.cms.utils.CommonUtil;
import com.cn.cms.utils.StringUtils;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by Administrator on 2017/11/6 0006.
 */
@Setter
@Getter
public class Permission extends Base {
    /**
     * 栏目名称
     */
    private String name;

    /**
     * 父类id
     */
    private Integer parentId;

    /**
     * 后台地址
    * */
    private String url;

    /**
     * 排序
     */
    private Integer sort;

    /**
     * 说明
     */
    private String description;

    /**
     * 权限名称
     */
    private String powerKey;

    /**
     * 权限值 对应 该栏目id
     */
    private String powerVal;

    public String getPowerKey(){
        String url = this.url;
        String name = "";
        for (String val : url.split("/")){
            if(val.length()<1) continue;
            String big = CommonUtil.captureName(val);
            name += big;
        };
        return name;
    }

    public Integer getPowerVal(){
        return this.id;
    }
}
