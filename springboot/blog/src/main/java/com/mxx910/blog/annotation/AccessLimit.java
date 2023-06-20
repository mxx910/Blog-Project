package com.mxx910.blog.annotation;

import java.lang.annotation.*;

/**
 * @author mxx910
 * @description: 限流注解 redis
 */
@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface AccessLimit {

    /**
     * 限制周期(秒)
     */
    int seconds();

    /**
     * 规定周期内限制次数
     */
    int maxCount();

    /**
     * 触发限制时的消息提示
     */
    String msg() default "操作过于频繁请稍后再试";

}
