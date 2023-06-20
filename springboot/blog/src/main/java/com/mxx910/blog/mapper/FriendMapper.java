package com.mxx910.blog.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import com.mxx910.blog.entity.Friend;
import com.mxx910.blog.model.VO.FriendBackVO;
import com.mxx910.blog.model.VO.FriendVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author mxx910
 */
@Mapper
public interface FriendMapper extends BaseMapper<Friend> {
    /**
     * 查看友链列表
     *
     * @return 友链列表
     */
    List<FriendVO> selectFriendVOList();

    /**
     * 查看友链后台列表
     *
     * @param limit   页码
     * @param size    大小
     * @param keyword 条件
     * @return 友链后台列表
     */
    List<FriendBackVO> selectFriendBackVOList(@Param("limit") Long limit, @Param("size") Long size, @Param("keyword") String keyword);

}
