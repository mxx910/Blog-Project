package com.mxx910.blog.controller;

import com.mxx910.blog.annotation.VisitLogger;
import com.mxx910.blog.model.DTO.ConditionDTO;
import com.mxx910.blog.model.DTO.Result;
import com.mxx910.blog.service.PhotoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @author: mxx910
 * @date: 2023/6/12
 * @description:
 */
@Api(tags = "照片模块")
@RestController
public class PhotoController {
    @Autowired
    private PhotoService photoService;

    /**
     * 查看照片列表
     *
     * @return {@link Result<PhotoVO> 照片列表
     */
    @VisitLogger(value = "照片")
    @ApiOperation(value = "查看照片列表")
    @GetMapping("/photo/list")
    public Result<Map<String, Object>> listPhotoVO(ConditionDTO condition) {
        return Result.success(photoService.listPhotoVO(condition));
    }
}
