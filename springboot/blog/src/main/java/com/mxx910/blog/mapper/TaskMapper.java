package com.mxx910.blog.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mxx910.blog.entity.Task;
import com.mxx910.blog.model.DTO.ConditionDTO;
import com.mxx910.blog.model.VO.TaskBackVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author: mxx910
 * @date: 2023/6/16
 * @description:
 */
@Mapper
public interface TaskMapper extends BaseMapper<Task> {

    /**
     * 查询定时任务数量
     *
     * @param condition 条件
     * @return 数量
     */
    Long countTaskBackVO(@Param("condition") ConditionDTO condition);

    /**
     * 查询定时任务列表
     *
     * @param limit     页码
     * @param size      大小
     * @param condition 条件
     * @return 定时任务列表
     */
    List<TaskBackVO> selectTaskBackVO(@Param("limit") Long limit, @Param("size") Long size, @Param("condition") ConditionDTO condition);
}


