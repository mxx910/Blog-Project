package com.mxx910.blog.annotation;

import java.lang.annotation.*;

/**
 * 操作日志注解
 * @author mxx910
 */
@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface OptLogger {

    /**
     * @return 操作描述
     */
    String value() default "";

}

