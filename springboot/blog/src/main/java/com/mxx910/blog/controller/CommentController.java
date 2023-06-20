package com.mxx910.blog.controller;

import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.dev33.satoken.annotation.SaCheckPermission;
import com.mxx910.blog.annotation.AccessLimit;
import com.mxx910.blog.enums.LikeTypeEnum;
import com.mxx910.blog.model.DTO.CommentDTO;
import com.mxx910.blog.model.DTO.ConditionDTO;
import com.mxx910.blog.model.DTO.Result;
import com.mxx910.blog.model.VO.CommentVO;
import com.mxx910.blog.model.VO.PageResult;
import com.mxx910.blog.model.VO.RecentCommentVO;
import com.mxx910.blog.model.VO.ReplyVO;
import com.mxx910.blog.service.CommentService;
import com.mxx910.blog.strategy.context.LikeStrategyContext;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author: mxx910
 * @date: 2023/4/25
 * @description: 评论控制器
 */
@Api(tags = "评论模块")
@RestController
public class CommentController {
    @Autowired
    private CommentService commentService;
    @Autowired
    private LikeStrategyContext likeStrategyContext;

    @ApiOperation(value = "查看最新评论")
    @GetMapping("/recent/comment")
    public Result<List<RecentCommentVO>> listRecentCommentVO() {
        return Result.success(commentService.listRecentCommentVO());
    }

    /**
     * 查看评论
     *
     * @param condition 条件
     * @return {@link Result<CommentVO>}
     */
    @ApiOperation(value = "查看评论")
    @GetMapping("/comment/list")
    public Result<PageResult<CommentVO>> listCommentVO(ConditionDTO condition) {
        return Result.success(commentService.listCommentVO(condition));
    }


    /**
     * 添加评论
     *
     * @param comment 评论信息
     * @return {@link Result<>}
     */
    @SaCheckLogin
    @ApiOperation(value = "添加评论")
    @SaCheckPermission("news:comment:add")
    @PostMapping("/comment/add")
    public Result<?> addComment(@Validated @RequestBody CommentDTO comment) {
        commentService.addComment(comment);
        return Result.success();
    }
    /**
     * 点赞评论
     *
     * @param commentId 评论id
     * @return {@link Result<>}
     */
    @SaCheckLogin
    @ApiOperation(value = "点赞评论")
    @AccessLimit(seconds = 60, maxCount = 3)
    @SaCheckPermission("news:comment:like")
    @PostMapping("/comment/{commentId}/like")
    public Result<?> likeComment(@PathVariable("commentId") Integer commentId) {
        likeStrategyContext.executeLikeStrategy(LikeTypeEnum.COMMENT, commentId);
        return Result.success();
    }
    /**
     * 查看回复评论
     *
     * @param commentId 评论id
     * @return {@link Result<ReplyVO>} 回复评论列表
     */
    @ApiOperation(value = "查看回复评论")
    @GetMapping("/comment/{commentId}/reply")
    public Result<List<ReplyVO>> listReply(@PathVariable("commentId") Integer commentId) {
        return Result.success(commentService.listReply(commentId));
    }
}
