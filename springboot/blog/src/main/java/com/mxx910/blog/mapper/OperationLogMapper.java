package com.mxx910.blog.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mxx910.blog.entity.OperationLog;
import com.mxx910.blog.model.DTO.ConditionDTO;
import com.mxx910.blog.model.VO.OperationLogVO;
import org.springframework.stereotype.Repository;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * @author: mxx910
 * @date: 2023/6/20
 * @description:
 */
@Repository
public interface OperationLogMapper extends BaseMapper<OperationLog> {

    /**
     * 查询操作日志
     *
     * @param limit     页码
     * @param size      大小
     * @param condition 条件
     * @return 操作日志列表
     */
    List<OperationLogVO> selectOperationLogVOList(@Param("limit") Long limit, @Param("size") Long size, @Param("condition") ConditionDTO condition);

}
