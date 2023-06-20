package com.mxx910.blog.service.impl;

import com.alibaba.fastjson2.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mxx910.blog.entity.Talk;
import com.mxx910.blog.mapper.CommentMapper;
import com.mxx910.blog.mapper.TalkMapper;
import com.mxx910.blog.model.VO.CommentCountVO;
import com.mxx910.blog.model.VO.PageResult;
import com.mxx910.blog.model.VO.TalkVO;
import com.mxx910.blog.service.RedisService;
import com.mxx910.blog.service.TalkService;
import com.mxx910.blog.utils.CommonUtils;
import com.mxx910.blog.utils.HTMLUtils;
import com.mxx910.blog.utils.PageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.mxx910.blog.constant.RedisConstant.TALK_LIKE_COUNT;
import static com.mxx910.blog.enums.ArticleStatusEnum.PUBLIC;
import static com.mxx910.blog.enums.CommentTypeEnum.TALK;

/**
 * @author: mxx910
 * @date: 2023/6/12
 * @description:
 */
@Service
public class TalkServiceImpl extends ServiceImpl<TalkMapper, Talk> implements TalkService {
    @Autowired
    TalkMapper talkMapper;
    @Autowired
    CommentMapper commentMapper;
    @Autowired
    private RedisService redisService;

    @Override
    public PageResult<TalkVO> listTalkVO() {
        // 查询说说总量
        Long count = talkMapper.selectCount((new LambdaQueryWrapper<Talk>()
                .eq(Talk::getStatus, PUBLIC.getStatus())));
        if (count == 0) {
            return new PageResult<>();
        }
        // 分页查询说说
        List<TalkVO> talkVOList = talkMapper.selectTalkList(PageUtils.getLimit(), PageUtils.getSize());
        // 查询说说评论量
        List<Integer> talkIdList = talkVOList.stream()
                .map(TalkVO::getId)
                .collect(Collectors.toList());
        List<CommentCountVO> commentCountVOList = commentMapper.selectCommentCountByTypeId(talkIdList, TALK.getType());
        Map<Integer, Integer> commentCountMap = commentCountVOList.stream()
                .collect(Collectors.toMap(CommentCountVO::getId, CommentCountVO::getCommentCount));
        // 查询说说点赞量
        Map<String, Integer> likeCountMap = redisService.getHashAll(TALK_LIKE_COUNT);
        // 封装说说
        talkVOList.forEach(item -> {
            item.setLikeCount(Optional.ofNullable(likeCountMap.get(item.getId().toString())).orElse(0));
            item.setCommentCount(Optional.ofNullable(commentCountMap.get(item.getId())).orElse(0));
            // 转换图片格式
            if (Objects.nonNull(item.getImages())) {
                item.setImgList(CommonUtils.castList(JSON.parseObject(item.getImages(), List.class), String.class));
            }
        });
        return new PageResult<>(talkVOList, count);
    }

    @Override
    public List<String> listTalkHome() {
        // 查询最新5条说说
        List<Talk> talkList = talkMapper.selectList(new LambdaQueryWrapper<Talk>()
                .select(Talk::getTalkContent)
                .eq(Talk::getStatus, PUBLIC.getStatus())
                .orderByDesc(Talk::getIsTop)
                .orderByDesc(Talk::getId)
                .last("limit 5"));
        return talkList.stream()
                .map(item -> item.getTalkContent().length() > 200
                        ? HTMLUtils.deleteHtmlTag(item.getTalkContent().substring(0, 200))
                        : HTMLUtils.deleteHtmlTag(item.getTalkContent()))
                .collect(Collectors.toList());
    }
}
