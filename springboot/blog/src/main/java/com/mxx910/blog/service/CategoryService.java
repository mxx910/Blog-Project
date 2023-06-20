package com.mxx910.blog.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mxx910.blog.entity.Category;
import com.mxx910.blog.model.VO.CategoryOptionVO;
import com.mxx910.blog.model.VO.CategoryVO;

import java.util.List;

public interface CategoryService extends IService<Category> {
    /**
     * 查看分类列表
     *
     * @return 分类列表
     */
    List<CategoryVO> listCategoryVO();

    List<CategoryOptionVO> listCategoryOption();
}
