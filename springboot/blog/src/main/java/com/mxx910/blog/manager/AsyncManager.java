package com.mxx910.blog.manager;

import com.mxx910.blog.utils.SpringUtils;
import com.mxx910.blog.utils.ThreadUtils;

import java.util.TimerTask;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author: mxx910
 * @date: 2023/6/20
 * @description:
 */
public class AsyncManager {

    /**
     * 单例模式，确保类只有一个实例
     */
    private AsyncManager() {
    }

    /**
     * 饿汉式，在类加载的时候立刻进行实例化
     */
    private static final AsyncManager INSTANCE = new AsyncManager();

    public static AsyncManager getInstance() {
        return INSTANCE;
    }

    /**
     * 异步操作任务调度线程池
     */
    private final ScheduledExecutorService executor = SpringUtils.getBean("scheduledExecutorService");

    /**
     * 执行任务
     *
     * @param task 任务
     */
    public void execute(TimerTask task) {
        executor.schedule(task, 10, TimeUnit.MILLISECONDS);
    }

    /**
     * 停止任务线程池
     */
    public void shutdown() {
        ThreadUtils.shutdownAndAwaitTermination(executor);
    }

}

