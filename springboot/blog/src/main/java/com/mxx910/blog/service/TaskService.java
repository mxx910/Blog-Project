package com.mxx910.blog.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mxx910.blog.entity.Task;
import com.mxx910.blog.model.DTO.ConditionDTO;
import com.mxx910.blog.model.DTO.StatusDTO;
import com.mxx910.blog.model.DTO.TaskDTO;
import com.mxx910.blog.model.DTO.TaskRunDTO;
import com.mxx910.blog.model.VO.PageResult;
import com.mxx910.blog.model.VO.TaskBackVO;

import java.util.List;

/**
 * @author: mxx910
 * @date: 2023/6/16
 * @description:
 */
public interface TaskService extends IService<Task> {

    /**
     * 查看定时任务列表
     *
     * @param condition 条件
     * @return 定时任务列表
     */
    PageResult<TaskBackVO> listTaskBackVO(ConditionDTO condition);

    /**
     * 添加定时任务
     *
     * @param task 定时任务
     */
    void addTask(TaskDTO task);

    /**
     * 修改定时任务
     *
     * @param task 定时任务信息
     */
    void updateTask(TaskDTO task);

    /**
     * 删除定时任务
     *
     * @param taskIdList 定时任务id集合
     */
    void deleteTask(List<Integer> taskIdList);

    /**
     * 修改定时任务状态
     *
     * @param taskStatus 定时任务状态
     */
    void updateTaskStatus(StatusDTO taskStatus);

    /**
     * 定时任务立即执行一次
     *
     * @param taskRun 定时任务
     */
    void runTask(TaskRunDTO taskRun);
}

