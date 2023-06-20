package com.mxx910.blog.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import com.mxx910.blog.annotation.OptLogger;
import com.mxx910.blog.model.DTO.Result;
import com.mxx910.blog.service.BlogFileService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import static com.mxx910.blog.constant.OptTypeConstant.UPLOAD;

/**
 * @author: mxx910
 * @date: 2023/6/14
 * @description:
 */
@Api(tags = "文件模块")
@RestController
public class BlogFileController {
    @Autowired
    BlogFileService blogFileService;
    /**
     * 上传文件
     *
     * @param file 文件
     * @return {@link Result<>}
     */
    @OptLogger(value = UPLOAD)
    @ApiOperation(value = "上传文件")
    @ApiImplicitParam(name = "file", value = "图片", required = true, dataType = "MultipartFile")
    @SaCheckPermission("system:file:upload")
    @PostMapping("/admin/file/upload")
    public Result<?> uploadFile(@RequestParam("file") MultipartFile file, @RequestParam("path") String path) {
        blogFileService.uploadFile(file, path);
        return Result.success();
    }
}
