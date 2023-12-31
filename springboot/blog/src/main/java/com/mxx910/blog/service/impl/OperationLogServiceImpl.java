package com.mxx910.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mxx910.blog.entity.OperationLog;
import com.mxx910.blog.mapper.OperationLogMapper;
import com.mxx910.blog.model.DTO.ConditionDTO;
import com.mxx910.blog.model.VO.OperationLogVO;
import com.mxx910.blog.model.VO.PageResult;
import com.mxx910.blog.service.OperationLogService;
import com.mxx910.blog.utils.PageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * @author: mxx910
 * @date: 2023/6/20
 * @description:
 */
@Service
public class OperationLogServiceImpl extends ServiceImpl<OperationLogMapper, OperationLog> implements OperationLogService {

    @Autowired
    private OperationLogMapper operationLogMapper;

    @Override
    public PageResult<OperationLogVO> listOperationLogVO(ConditionDTO condition) {
        // 查询操作日志数量
        Long count = operationLogMapper.selectCount(new LambdaQueryWrapper<OperationLog>()
                .like(StringUtils.hasText(condition.getOptModule()), OperationLog::getModule, condition.getOptModule())
                .or()
                .like(StringUtils.hasText(condition.getKeyword()), OperationLog::getDescription, condition.getKeyword())
        );
        if (count == 0) {
            return new PageResult<>();
        }
        // 查询操作日志列表
        List<OperationLogVO> operationLogVOList = operationLogMapper.selectOperationLogVOList(PageUtils.getLimit(),
                PageUtils.getSize(), condition);
        return new PageResult<>(operationLogVOList, count);
    }

    @Override
    public void saveOperationLog(OperationLog operationLog) {
        // 保存操作日志
        operationLogMapper.insert(operationLog);
    }

}
