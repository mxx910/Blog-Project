package com.mxx910.blog.manager.factory;

import com.mxx910.blog.entity.ExceptionLog;
import com.mxx910.blog.entity.OperationLog;
import com.mxx910.blog.entity.VisitLog;
import com.mxx910.blog.service.ExceptionLogService;
import com.mxx910.blog.service.OperationLogService;
import com.mxx910.blog.service.VisitLogService;
import com.mxx910.blog.utils.SpringUtils;

import java.util.TimerTask;

/**
 * @author: mxx910
 * @date: 2023/6/20
 * @description:
 */
public class AsyncFactory {

    /**
     * 记录操作日志
     *
     * @param operationLog 操作日志信息
     * @return 任务task
     */
    public static TimerTask recordOperation(OperationLog operationLog) {
        return new TimerTask() {
            @Override
            public void run() {
                SpringUtils.getBean(OperationLogService.class).saveOperationLog(operationLog);
            }
        };
    }

    /**
     * 记录异常日志
     *
     * @param exceptionLog 异常日志信息
     * @return 任务task
     */
    public static TimerTask recordException(ExceptionLog exceptionLog) {
        return new TimerTask() {
            @Override
            public void run() {
                SpringUtils.getBean(ExceptionLogService.class).saveExceptionLog(exceptionLog);
            }
        };
    }

    /**
     * 记录访问日志
     *
     * @param visitLog 访问日志信息
     * @return 任务task
     */
    public static TimerTask recordVisit(VisitLog visitLog) {
        return new TimerTask() {
            @Override
            public void run() {
                SpringUtils.getBean(VisitLogService.class).saveVisitLog(visitLog);
            }
        };
    }
}
