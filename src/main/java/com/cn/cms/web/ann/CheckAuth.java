package com.cn.cms.web.ann;

import java.lang.annotation.*;

/**
 * Created by Administrator on 2017/11/30 0030.
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface CheckAuth {

    String name() default "";
}
