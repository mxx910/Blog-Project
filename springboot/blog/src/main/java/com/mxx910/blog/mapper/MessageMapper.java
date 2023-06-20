package com.mxx910.blog.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mxx910.blog.entity.Message;
import com.mxx910.blog.model.DTO.ConditionDTO;
import com.mxx910.blog.model.VO.MessageBackVO;
import com.mxx910.blog.model.VO.MessageVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author: mxx910
 * @date: 2023/4/24
 * @description:
 */
@Mapper
public interface MessageMapper extends BaseMapper<Message> {

    /**
     * 查询留言列表
     *
     * @return 留言列表
     */
    List<MessageVO> selectMessageVOList();

    /**
     * 查询后台留言列表
     *
     * @param limit     页码
     * @param size      大小
     * @param condition 条件
     * @return 后台留言列表
     */
    List<MessageBackVO> selectMessageBackVOList(@Param("limit") Long limit, @Param("size") Long size, @Param("condition") ConditionDTO condition);
}
