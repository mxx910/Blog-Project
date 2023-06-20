package com.mxx910.blog.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mxx910.blog.entity.Tag;
import com.mxx910.blog.model.DTO.ConditionDTO;
import com.mxx910.blog.model.DTO.Result;
import com.mxx910.blog.model.VO.ArticleConditionList;
import com.mxx910.blog.model.VO.TagOptionVO;
import com.mxx910.blog.model.VO.TagVO;

import java.util.List;

/**
 * @author mxx910
 */
public interface TagService extends IService<Tag> {
    /**
     * 查看分类列表
     *
     * @return 分类列表
     */
    List<TagVO> listTagVO();

    ArticleConditionList listArticleTag(ConditionDTO condition);

    List<TagOptionVO> listTagOption();
}
