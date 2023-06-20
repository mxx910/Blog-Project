package com.mxx910.blog.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mxx910.blog.entity.Category;
import com.mxx910.blog.model.VO.CategoryBackVO;
import com.mxx910.blog.model.VO.CategoryVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author: mxx910
 * @date: 2023/4/24
 * @description:
 */
@Mapper
public interface CategoryMapper extends BaseMapper<Category> {
    /**
     * 查询后台分类列表
     *
     * @param limit   页码
     * @param size    大小
     * @param keyword 关键字
     * @return 后台分类列表
     */
    List<CategoryBackVO> selectCategoryBackVO(@Param("limit") Long limit, @Param("size") Long size, @Param("keyword") String keyword);

    /**
     * 查询分类列表
     *
     * @return 分类列表
     */
    List<CategoryVO> selectCategoryVO();
}
