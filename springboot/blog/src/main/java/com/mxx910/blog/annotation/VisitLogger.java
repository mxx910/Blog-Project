package com.mxx910.blog.annotation;

import java.lang.annotation.*;

/**
 * @author mxx910
 */
@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface VisitLogger {

    /**
     * @return 访问页面
     */
    String value() default "";
}

