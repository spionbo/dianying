package com.cn.cms.web.ann;

import java.lang.annotation.*;

/**
 * Created by Administrator on 2017/12/21 0021.
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface NotSaveBody {
}
