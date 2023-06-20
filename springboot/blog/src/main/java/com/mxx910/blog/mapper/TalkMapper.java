package com.mxx910.blog.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mxx910.blog.entity.Talk;
import com.mxx910.blog.model.VO.TalkVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TalkMapper extends BaseMapper<Talk> {

//    /**
//     * 查询后台说说列表
//     *
//     * @param limit  页码
//     * @param size   大小
//     * @param status 状态
//     * @return 后台说说列表
//     */
//    List<TalkBackVO> selectTalkBackVO(@Param("limit") Long limit, @Param("size") Long size, @Param("status") Integer status);
//
//    /**
//     * 根据id查询后台说说
//     *
//     * @param talkId 说说id
//     * @return 后台说说
//     */
//    TalkBackInfoVO selectTalkBackById(Integer talkId);

    /**
     * 根据id查询说说
     *
     * @param talkId 说说id
     * @return 说说
     */
    TalkVO selectTalkById(Integer talkId);

    /**
     * 查询说说列表
     *
     * @param limit 页码
     * @param size  大小
     * @return 说说列表
     */
    List<TalkVO> selectTalkList(@Param("limit") Long limit, @Param("size") Long size);
}
