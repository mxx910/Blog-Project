package com.mxx910.blog.controller;

import com.mxx910.blog.annotation.VisitLogger;
import com.mxx910.blog.model.DTO.ConditionDTO;
import com.mxx910.blog.model.DTO.Result;
import com.mxx910.blog.model.VO.ArticleConditionList;
import com.mxx910.blog.model.VO.TagOptionVO;
import com.mxx910.blog.service.TagService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.mxx910.blog.model.VO.TagVO;
import java.util.List;

/**
 * @author: mxx910
 * @date: 2023/6/12
 * @description:
 */
@Api(tags = "标签模块")
@RestController
public class TagController {
    @Autowired
    private TagService tagService;
    /**
     * 查看标签列表
     *
     * @return {@link Result<TagVO>} 标签列表
     */
    @VisitLogger(value = "文章标签")
    @ApiOperation(value = "查看标签列表")
    @GetMapping("/tag/list")
    public Result<List<TagVO>> listTagVO() {
        return Result.success(tagService.listTagVO());
    }
    /**
     * 查看标签下的文章
     *
     * @param condition 查询条件
     * @return 文章列表
     */
    @VisitLogger(value = "标签文章")
    @ApiOperation(value = "查看标签下的文章")
    @GetMapping("/tag/article")
    public Result<ArticleConditionList> listArticleTag(ConditionDTO condition) {
        return Result.success(tagService.listArticleTag(condition));
    }

    /**
     * 查看标签选项
     *
     * @return {@link Result<TagOptionVO>} 标签列表
     */
    @ApiOperation(value = "查看标签选项")
    @GetMapping("/admin/tag/option")
    public Result<List<TagOptionVO>> listTagOption() {
        return Result.success(tagService.listTagOption());
    }


}
