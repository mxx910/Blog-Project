package com.mxx910.blog.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mxx910.blog.entity.Friend;
import com.mxx910.blog.model.DTO.ConditionDTO;
import com.mxx910.blog.model.DTO.FriendDTO;
import com.mxx910.blog.model.VO.FriendBackVO;
import com.mxx910.blog.model.VO.FriendVO;
import com.mxx910.blog.model.VO.PageResult;

import java.util.List;

/**
 * @author: mxx910
 * @date: 2023/6/14
 * @description:
 */
public interface FriendService extends IService<Friend> {

    /**
     * 查看友链列表
     *
     * @return 友链列表
     */
    List<FriendVO> listFriendVO();

    /**
     * 查看后台友链列表
     *
     * @param condition 查询条件
     * @return 后台友链列表
     */
    PageResult<FriendBackVO> listFriendBackVO(ConditionDTO condition);

    /**
     * 添加友链
     *
     * @param friend 友链
     */
    void addFriend(FriendDTO friend);

    /**
     * 修改友链
     *
     * @param friend 友链
     */
    void updateFriend(FriendDTO friend);
}
