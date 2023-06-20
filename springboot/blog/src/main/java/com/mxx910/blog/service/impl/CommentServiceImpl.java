package com.mxx910.blog.service.impl;

import cn.dev33.satoken.stp.StpUtil;

import cn.hutool.core.lang.Assert;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mxx910.blog.constant.CommonConstant;
import com.mxx910.blog.entity.*;
import com.mxx910.blog.mapper.ArticleMapper;
import com.mxx910.blog.mapper.CommentMapper;
import com.mxx910.blog.mapper.TalkMapper;
import com.mxx910.blog.mapper.UserMapper;
import com.mxx910.blog.model.DTO.CommentDTO;
import com.mxx910.blog.model.DTO.ConditionDTO;
import com.mxx910.blog.model.DTO.MailDTO;
import com.mxx910.blog.model.VO.*;
import com.mxx910.blog.service.CommentService;
import com.mxx910.blog.service.RedisService;
import com.mxx910.blog.service.SiteConfigService;
import com.mxx910.blog.utils.HTMLUtils;
import com.mxx910.blog.utils.PageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.mxx910.blog.enums.CommentTypeEnum;
import java.util.*;
import java.util.stream.Collectors;

import static com.mxx910.blog.constant.CommonConstant.TRUE;
import static com.mxx910.blog.constant.RedisConstant.COMMENT_LIKE_COUNT;

/**
 * @author: mxx910
 * @date: 2023/4/25
 * @description:
 */
@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements CommentService {
    @Autowired
    CommentMapper commentMapper;

    @Autowired
    private RedisService redisService;
    @Autowired
    private ArticleMapper articleMapper;
    @Autowired
    private TalkMapper talkMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private SiteConfigService siteConfigService;
    @Override
    public List<RecentCommentVO> listRecentCommentVO() {
        return commentMapper.selectRecentComment();
    }

    @Override
    public PageResult<CommentVO> listCommentVO(ConditionDTO condition) {
        Long count = commentMapper.selectCount(new LambdaQueryWrapper<Comment>()
                .eq(Objects.nonNull(condition.getTypeId()), Comment::getTypeId, condition.getTypeId())
                .eq(Comment::getCommentType, condition.getCommentType())
                .eq(Comment::getIsCheck, TRUE).isNull(Comment::getParentId));
        if (count == 0) {
            return new PageResult<>();
        }
        List<CommentVO> commentVOList = commentMapper.selectParentComment(PageUtils.getLimit(), PageUtils.getSize(), condition);
        if (CollectionUtils.isEmpty(commentVOList)) {
            return new PageResult<>();
        }
        // 评论点赞
        Map<String, Integer> likeCountMap = redisService.getHashAll(COMMENT_LIKE_COUNT);
        // 父评论id集合
        List<Integer> parentCommentIdList = commentVOList.stream().map(CommentVO::getId).collect(Collectors.toList());
        // 分组查询每组父评论下的子评论前三条
        List<ReplyVO> replyVOList = commentMapper.selectReplyByParentIdList(parentCommentIdList);
        // 封装子评论点赞量
        replyVOList.forEach(item -> item.setLikeCount(Optional.ofNullable(likeCountMap.get(item.getId().toString())).orElse(0)));
        // 根据父评论id生成对应子评论的Map
        Map<Integer, List<ReplyVO>> replyMap = replyVOList.stream().collect(Collectors.groupingBy(ReplyVO::getParentId));
        // 父评论的回复数量
        List<ReplyCountVO> replyCountList = commentMapper.selectReplyCountByParentId(parentCommentIdList);
        // 转换Map
        Map<Integer, Integer> replyCountMap = replyCountList.stream().collect(Collectors.toMap(ReplyCountVO::getCommentId, ReplyCountVO::getReplyCount));
        // 封装评论数据
        commentVOList.forEach(item -> {
            item.setLikeCount(Optional.ofNullable(likeCountMap.get(item.getId().toString())).orElse(0));
            item.setReplyVOList(replyMap.get(item.getId()));
            item.setReplyCount(Optional.ofNullable(replyCountMap.get(item.getId())).orElse(0));
        });
        return new PageResult<>(commentVOList, count);
    }

    @Override
    public List<ReplyVO> listReply(Integer commentId) {
        // 分页查询子评论
        List<ReplyVO> replyVOList = commentMapper.selectReplyByParentId(PageUtils.getLimit(), PageUtils.getSize(), commentId);
        // 子评论点赞Map
        Map<String, Integer> likeCountMap = redisService.getHashAll(COMMENT_LIKE_COUNT);
        replyVOList.forEach(item -> item.setLikeCount(likeCountMap.get(item.getId().toString())));
        return replyVOList;
    }

    @Override
    public void addComment(CommentDTO comment) {
        verifyComment(comment);
        SiteConfig siteConfig = siteConfigService.getSiteConfig();
        Integer commentCheck = siteConfig.getCommentCheck();
        // 过滤标签
        comment.setCommentContent(HTMLUtils.filter(comment.getCommentContent()));
        Comment newComment = Comment.builder()
                .fromUid(StpUtil.getLoginIdAsInt())
                .toUid(comment.getToUid())
                .typeId(comment.getTypeId())
                .commentType(comment.getCommentType())
                .parentId(comment.getParentId())
                .replyId(comment.getReplyId())
                .commentContent(comment.getCommentContent())
                .isCheck(commentCheck.equals(CommonConstant.FALSE) ? TRUE : CommonConstant.FALSE)
                .build();
        commentMapper.insert(newComment);
        String fromNickname = userMapper.selectOne(new LambdaQueryWrapper<User>()
                        .select(User::getNickname)
                        .eq(User::getId, StpUtil.getLoginIdAsInt()))
                .getNickname();
//        // 通知用户
//        if (siteConfig.getEmailNotice().equals(TRUE)) {
//            CompletableFuture.runAsync(() -> {
//                notice(newComment, fromNickname);
//            });
//        }
    }    /**
     //     * 评论通知
     //     *
     //     * @param comment      评论
     //     * @param fromNickname 评论用户昵称
     //     */
//    private void notice(Comment comment, String fromNickname) {
//        // 自己回复自己不用提醒
//        if (comment.getFromUid().equals(comment.getToUid())) {
//            return;
//        }
//        // 邮件标题
//        String title = "友链";
//        // 回复用户id
//        Integer toUid = BLOGGER_ID;
//        // 父评论
//        if (Objects.isNull(comment.getParentId())) {
//            if (comment.getCommentType().equals(CommentTypeEnum.ARTICLE.getType())) {
//                Article article = articleMapper.selectOne(new LambdaQueryWrapper<Article>()
//                        .select(Article::getArticleTitle, Article::getUserId)
//                        .eq(Article::getId, comment.getTypeId()));
//                title = article.getArticleTitle();
//                toUid = article.getUserId();
//            }
//            if (comment.getCommentType().equals(CommentTypeEnum.TALK.getType())) {
//                title = "说说";
//                toUid = talkMapper.selectOne(new LambdaQueryWrapper<Talk>()
//                                .select(Talk::getUserId)
//                                .eq(Talk::getId, comment.getTypeId()))
//                        .getUserId();
//            }
//            // 自己评论自己的作品，不用提醒
//            if(comment.getFromUid().equals(toUid)){
//                return;
//            }
//        } else {
//            // 子评论
//            toUid = comment.getToUid();
//            if (comment.getCommentType().equals(CommentTypeEnum.ARTICLE.getType())) {
//                title = articleMapper.selectOne(new LambdaQueryWrapper<Article>()
//                                .select(Article::getArticleTitle)
//                                .eq(Article::getId, comment.getTypeId()))
//                        .getArticleTitle();
//            }
//            if (comment.getCommentType().equals(CommentTypeEnum.TALK.getType())) {
//                title = "说说";
//            }
//        }
//        // 查询回复用户邮箱、昵称、id
//        User toUser = userMapper.selectOne(new LambdaQueryWrapper<User>()
//                .select(User::getEmail, User::getNickname)
//                .eq(User::getId, toUid));
//        // 邮箱不为空
//        if (StringUtils.hasText(toUser.getEmail())) {
//            sendEmail(comment, toUser, title, fromNickname);
//        }
//    }
//
    private void verifyComment(CommentDTO comment){
        if (comment.getCommentType().equals(CommentTypeEnum.ARTICLE.getType())){
            Article article = articleMapper.selectOne(new LambdaQueryWrapper<Article>().select(Article::getId).eq(Article::getId, comment.getTypeId()));
            Assert.notNull(article, "文章不存在");
        }
        if (comment.getCommentType().equals(CommentTypeEnum.TALK.getType())) {
            Talk talk = talkMapper.selectOne(new LambdaQueryWrapper<Talk>().select(Talk::getId).eq(Talk::getId, comment.getTypeId()));
            Assert.notNull(talk, "说说不存在");
        }
        Optional.ofNullable(comment.getParentId()).ifPresent(parentId -> {
            Comment parentComment = commentMapper.selectOne(new LambdaQueryWrapper<Comment>().select(Comment::getId, Comment::getParentId, Comment::getCommentType).eq(Comment::getId, parentId));
            Assert.notNull(parentComment, "父评论不存在");
            Assert.isNull(parentComment.getParentId(), "当前评论为子评论，不能作为父评论");
            Assert.isTrue(comment.getCommentType().equals(parentComment.getCommentType()), "只能以同类型的评论作为父评论");
            Comment replyComment = commentMapper.selectOne(new LambdaQueryWrapper<Comment>().select(Comment::getId, Comment::getParentId, Comment::getFromUid, Comment::getCommentType).eq(Comment::getId, comment.getReplyId()));
            User toUser = userMapper.selectOne(new LambdaQueryWrapper<User>().select(User::getId).eq(User::getId, comment.getToUid()));
            Assert.notNull(replyComment, "回复的评论不存在");
            Assert.notNull(toUser, "回复的用户不存在");
            Assert.isTrue(comment.getCommentType().equals(replyComment.getCommentType()), "只能回复同类型的下的评论");
            if (Objects.nonNull(replyComment.getParentId())) {
                Assert.isTrue(replyComment.getParentId().equals(parentId), "提交的评论parentId与当前回复评论parentId不一致");
            }
            Assert.isTrue(replyComment.getFromUid().equals(comment.getToUid()), "提交的评论toUid与当前回复评论fromUid不一致");
            // 只能回复当前父评论及其子评论
            List<Integer> replyIdList = commentMapper.selectCommentIdByParentId(parentId);
            replyIdList.add(parentId);
            Assert.isTrue(replyIdList.contains(parentId), "当前父评论下不存在该子评论");
        });
    }

}
