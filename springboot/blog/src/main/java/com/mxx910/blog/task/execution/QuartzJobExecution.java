package com.mxx910.blog.task.execution;


import com.mxx910.blog.entity.Task;
import com.mxx910.blog.task.utils.TaskInvokeUtils;
import org.quartz.JobExecutionContext;

/**
 * 定时任务处理（允许并发执行）
 *
 * @author ican
 */
public class QuartzJobExecution extends AbstractQuartzJob {
    @Override
    protected void doExecute(JobExecutionContext context, Task task) throws Exception {
        TaskInvokeUtils.invokeMethod(task);
    }
}
