package com.mxx910.blog.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mxx910.blog.entity.OperationLog;
import com.mxx910.blog.model.DTO.ConditionDTO;
import com.mxx910.blog.model.VO.OperationLogVO;
import com.mxx910.blog.model.VO.PageResult;

/**
 * @author mxx910
 */
public interface OperationLogService extends IService<OperationLog> {

    /**
     * 查看操作日志列表
     *
     * @param condition 条件
     * @return 日志列表
     */
    PageResult<OperationLogVO> listOperationLogVO(ConditionDTO condition);

    /**
     * 保存操作日志
     *
     * @param operationLog 操作日志信息
     */
    void saveOperationLog(OperationLog operationLog);
}
