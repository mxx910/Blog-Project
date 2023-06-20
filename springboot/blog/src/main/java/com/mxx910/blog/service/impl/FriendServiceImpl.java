package com.mxx910.blog.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mxx910.blog.entity.Friend;
import com.mxx910.blog.mapper.FriendMapper;
import com.mxx910.blog.model.DTO.ConditionDTO;
import com.mxx910.blog.model.DTO.FriendDTO;
import com.mxx910.blog.model.VO.FriendBackVO;
import com.mxx910.blog.model.VO.FriendVO;
import com.mxx910.blog.model.VO.PageResult;
import com.mxx910.blog.service.FriendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author: mxx910
 * @date: 2023/6/14
 * @description:
 */
@Service
public class FriendServiceImpl extends ServiceImpl<FriendMapper, Friend> implements FriendService {

    @Autowired
    private FriendMapper friendMapper;

    @Override
    public List<FriendVO> listFriendVO() {
        return friendMapper.selectFriendVOList();
    }

    @Override
    public PageResult<FriendBackVO> listFriendBackVO(ConditionDTO condition) {
        return null;
    }

    @Override
    public void addFriend(FriendDTO friend) {

    }

    @Override
    public void updateFriend(FriendDTO friend) {

    }
}
