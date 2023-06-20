package com.mxx910.blog.constant;

/**
 * @author: mxx910
 * @date: 2023/6/16
 * @description:
 */
public class ScheduleConstant {

    /**
     * 执行类名
     */
    public static final String TASK_CLASS_NAME = "TASK_CLASS_NAME";

    /**
     * 执行目标key
     */
    public static final String TASK_PROPERTIES = "TASK_PROPERTIES";

    /**
     * 默认
     */
    public static final int MISFIRE_DEFAULT = 0;

    /**
     * 立即触发执行
     */
    public static final int MISFIRE_IGNORE_MISFIRES = 1;

    /**
     * 触发一次执行
     */
    public static final int MISFIRE_FIRE_AND_PROCEED = 2;

    /**
     * 不触发立即执行
     */
    public static final int MISFIRE_DO_NOTHING = 3;
}

