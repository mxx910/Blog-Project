package com.mxx910.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mxx910.blog.entity.Category;
import com.mxx910.blog.mapper.CategoryMapper;
import com.mxx910.blog.model.VO.CategoryOptionVO;
import com.mxx910.blog.model.VO.CategoryVO;
import com.mxx910.blog.service.CategoryService;
import com.mxx910.blog.utils.BeanCopyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author: mxx910
 * @date: 2023/6/12
 * @description:
 */
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {
    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    public List<CategoryVO> listCategoryVO() {
        return categoryMapper.selectCategoryVO();
    }

    @Override
    public List<CategoryOptionVO> listCategoryOption() {
        List<Category> categoryList = categoryMapper.selectList(new LambdaQueryWrapper<Category>()
                .orderByDesc(Category::getId));
        return BeanCopyUtils.copyBeanList(categoryList, CategoryOptionVO.class);

    }
}
