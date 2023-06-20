package com.mxx910.blog.controller;

import com.mxx910.blog.annotation.VisitLogger;
import com.mxx910.blog.model.DTO.Result;
import com.mxx910.blog.model.VO.AlbumVO;
import com.mxx910.blog.service.AlbumService;
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
@Api(tags = "相册模块")
@RestController
public class AlbumController {
    @Autowired
    private AlbumService albumService;
    /**
     * 查看相册列表
     *
     * @return {@link Result<AlbumVO> 相册列表
     */
    @VisitLogger(value = "相册")
    @ApiOperation(value = "查看相册列表")
    @GetMapping("/album/list")
    public Result<List<AlbumVO>> listAlbumVO() {
        return Result.success(albumService.listAlbumVO());
    }

}
