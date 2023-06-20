package com.mxx910.blog.controller;


import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.dev33.satoken.annotation.SaCheckPermission;
import com.mxx910.blog.annotation.AccessLimit;
import com.mxx910.blog.annotation.OptLogger;
import com.mxx910.blog.annotation.VisitLogger;
import com.mxx910.blog.enums.LikeTypeEnum;
import com.mxx910.blog.model.DTO.ArticleDTO;
import com.mxx910.blog.model.DTO.Result;
import com.mxx910.blog.model.VO.*;
import com.mxx910.blog.service.ArticleService;
import com.mxx910.blog.strategy.context.LikeStrategyContext;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

import static com.mxx910.blog.constant.OptTypeConstant.ADD;
import static com.mxx910.blog.constant.OptTypeConstant.UPLOAD;

/**
 * @author: mxx910
 * @date: 2023/5/5
 * @description:
 */
@Api(tags = "文章模块")
@RestController
public class ArticleController {
    @Autowired
    private ArticleService articleService;

    @Autowired
    private LikeStrategyContext likeStrategyContext;

    @VisitLogger(value = "首页")
    @ApiOperation(value = "查看首页文章列表")
    @GetMapping("/article/list")
    public Result<PageResult<ArticleHomeVO>> listArticleHomeVO() {
        return Result.success(articleService.listArticleHomeVO());
    }


    /**
     * 查看文章
     *
     * @param articleId 文章id
     * @return {@link Result<ArticleVO>} 首页文章
     */
    @VisitLogger(value = "文章")
    @ApiOperation(value = "查看文章")
    @GetMapping("/article/{articleId}")
    public Result<ArticleVO> getArticleHomeById(@PathVariable("articleId") Integer articleId) {
        return Result.success(articleService.getArticleHomeById(articleId));
    }

    /**
     * 查看文章归档
     *
     * @return {@link Result<ArchiveVO>} 文章归档列表
     */
    @VisitLogger(value = "归档")
    @ApiOperation(value = "查看文章归档")
    @GetMapping("/archives/list")
    public Result<PageResult<ArchiveVO>> listArchiveVO() {
        return Result.success(articleService.listArchiveVO());
    }

    /**
     * 上传文章图片
     *
     * @param file 文件
     * @return {@link Result<String>} 文章图片地址
     */
    @OptLogger(value = UPLOAD)
    @ApiOperation(value = "上传文章图片")
    @ApiImplicitParam(name = "file", value = "文章图片", required = true, dataType = "MultipartFile")
    @SaCheckPermission("blog:article:upload")
    @PostMapping("/admin/article/upload")
    public Result<String> saveArticleImages(@RequestParam("file") MultipartFile file) {
        return Result.success(articleService.saveArticleImages(file));
    }

    /**
     * 添加文章
     *
     * @param article 文章信息
     * @return {@link Result<>}
     */
    @OptLogger(value = ADD)
    @ApiOperation(value = "添加文章")
    @SaCheckPermission("blog:article:add")
    @PostMapping("/admin/article/add")
    public Result<Integer> addArticle(@Validated @RequestBody ArticleDTO article) {
        return Result.success(articleService.addArticle(article));

    }

    @SaCheckLogin
    @ApiOperation(value = "点赞文章")
    @AccessLimit(seconds = 60, maxCount = 3)
    @SaCheckPermission("blog:article:like")
    @PostMapping("/article/{articleId}/like")
    public Result<?> likeArticle(@PathVariable("articleId") Integer articleId) {
        likeStrategyContext.executeLikeStrategy(LikeTypeEnum.ARTICLE, articleId);
        return Result.success();
    }

    /**
     * 查看推荐文章
     *
     * @return {@link Result<ArticleRecommendVO>} 推荐文章
     */
    @ApiOperation(value = "查看推荐文章")
    @GetMapping("/article/recommend")
    public Result<List<ArticleRecommendVO>> listArticleRecommendVO() {
        return Result.success(articleService.listArticleRecommendVO());
    }
}
