package com.mxx910.blog.controller;

import com.mxx910.blog.annotation.VisitLogger;
import com.mxx910.blog.model.DTO.Result;
import com.mxx910.blog.model.VO.CategoryOptionVO;
import com.mxx910.blog.model.VO.CategoryVO;
import com.mxx910.blog.service.CategoryService;
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
@Api(tags = "博客模块")
@RestController
public class CategoryController {

    @Autowired
    CategoryService categoryService;
    /**
     * 查看分类列表
     *
     * @return {@link Result<CategoryDTO>} 分类列表
     */
    @VisitLogger(value = "文章分类")
    @ApiOperation(value = "查看分类列表")
    @GetMapping("/category/list")
    public Result<List<CategoryVO>> listCategoryVO() {
        return Result.success(categoryService.listCategoryVO());
    }

    /**
     * 查看分类选项
     *
     * @return {@link Result<CategoryOptionVO>} 分类列表
     */
    @ApiOperation(value = "查看分类选项")
    @GetMapping("/admin/category/option")
    public Result<List<CategoryOptionVO>> listCategoryOption() {
        return Result.success(categoryService.listCategoryOption());
    }
}
