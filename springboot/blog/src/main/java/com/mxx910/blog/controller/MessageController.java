package com.mxx910.blog.controller;

import com.mxx910.blog.annotation.AccessLimit;
import com.mxx910.blog.annotation.VisitLogger;
import com.mxx910.blog.model.DTO.MessageDTO;
import com.mxx910.blog.model.DTO.Result;
import com.mxx910.blog.model.VO.MessageVO;
import com.mxx910.blog.service.MessageService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author: mxx910
 * @date: 2023/6/19
 * @description:
 */
@Api(tags = "留言模块")
@RestController
public class MessageController {
    @Autowired
    private MessageService messageService;
    /**
     * 查看留言列表
     *
     * @return {@link MessageVO} 留言列表
     */
    @VisitLogger(value = "留言")
    @ApiOperation(value = "查看留言列表")
    @GetMapping("/message/list")
    public Result<List<MessageVO>> listMessageVO() {
        return Result.success(messageService.listMessageVO());
    }
    /**
     * 添加留言
     *
     * @param message 留言信息
     * @return {@link Result<>}
     */
    @AccessLimit(seconds = 60, maxCount = 3)
    @ApiOperation(value = "添加留言")
    @PostMapping("/message/add")
    public Result<?> addMessage(@Validated @RequestBody MessageDTO message) {
        messageService.addMessage(message);
        return Result.success();
    }
}
