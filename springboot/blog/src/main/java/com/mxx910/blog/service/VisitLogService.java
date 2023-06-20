package com.mxx910.blog.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mxx910.blog.entity.VisitLog;
import com.mxx910.blog.model.DTO.ConditionDTO;
import com.mxx910.blog.model.VO.PageResult;

/**
 * @author: mxx910
 * @date: 2023/6/20
 * @description:
 */
public interface VisitLogService extends IService<VisitLog> {

    /**
     * 保存访问日志
     *
     * @param visitLog 访问日志信息
     */
    void saveVisitLog(VisitLog visitLog);

    /**
     * 查看访问日志列表
     *
     * @param condition 条件
     * @return 日志列表
     */
    PageResult<VisitLog> listVisitLog(ConditionDTO condition);
}

