package com.mxx910.blog.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import com.mxx910.blog.model.DTO.ConditionDTO;
import com.mxx910.blog.model.VO.UserBackVO;
import com.mxx910.blog.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author: mxx910
 * @date: 2023/4/19
 * @description:
 */
@Mapper
public interface  UserMapper extends  BaseMapper<User> {

    /**
     * 查询后台用户列表
     * @param limit     页码
     * @param size      大小
     * @param condition 条件
     * @return 用户后台列表
     */
    List<UserBackVO> listUserBackVO(@Param("limit") Long limit, @Param("size") Long size, @Param("condition") ConditionDTO condition);

    /**
     * 查询用户后台数量
     * @param condition 条件
     * @return 用户数量
     */
    Long countUser(@Param("condition") ConditionDTO condition);

}
