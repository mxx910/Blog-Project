package com.mxx910.blog.controller;

import com.mxx910.blog.annotation.VisitLogger;
import com.mxx910.blog.model.DTO.Result;
import com.mxx910.blog.model.VO.PageResult;
import com.mxx910.blog.model.VO.TalkVO;
import com.mxx910.blog.service.TalkService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author: mxx910
 * @date: 2023/6/12
 * @description:
 */
@Api(tags = "说说模块")
@RestController
public class TalkController {
    @Autowired
    private TalkService talkService;
    /**
     * 查看说说列表
     *
     * @return {@link Result<TalkVO>}
     */
    @VisitLogger(value = "说说列表")
    @ApiOperation(value = "查看说说列表")
    @GetMapping("/talk/list")
    public Result<PageResult<TalkVO>> listTalkVO() {
        return Result.success(talkService.listTalkVO());
    }

    /**
     * 查看首页说说
     *
     * @return {@link Result<String>}
     */
    @ApiOperation(value = "查看首页说说")
    @GetMapping("/home/talk")
    public Result<List<String>> listTalkHome() {
        return Result.success(talkService.listTalkHome());
    }

}
