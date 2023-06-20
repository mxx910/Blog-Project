package com.mxx910.blog.controller;

import com.mxx910.blog.annotation.VisitLogger;
import com.mxx910.blog.model.DTO.Result;
import com.mxx910.blog.model.VO.FriendVO;
import com.mxx910.blog.service.FriendService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author: mxx910
 * @date: 2023/6/14
 * @description:
 */
@Api(tags = "友链模块")
@RestController
public class FriendController {
    @Autowired
    private FriendService friendService;

    /**
     * 查看友链列表
     *
     * @return {@link Result<FriendVO>} 友链列表
     */
    @VisitLogger(value = "友链")
    @ApiOperation(value = "查看友链列表")
    @GetMapping("/friend/list")
    public Result<List<FriendVO>> listFriendVO() {
        return Result.success(friendService.listFriendVO());
    }
}
