package com.mxx910.blog.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mxx910.blog.entity.ExceptionLog;
import com.mxx910.blog.model.DTO.ConditionDTO;
import com.mxx910.blog.model.VO.PageResult;

/**
 * @author mxx910
 */
public interface ExceptionLogService extends IService<ExceptionLog> {

    /**
     * 查看异常日志列表
     *
     * @param condition 条件
     * @return 日志列表
     */
    PageResult<ExceptionLog> listExceptionLog(ConditionDTO condition);

    /**
     * 保存异常日志
     *
     * @param exceptionLog 异常日志信息
     */
    void saveExceptionLog(ExceptionLog exceptionLog);
}
