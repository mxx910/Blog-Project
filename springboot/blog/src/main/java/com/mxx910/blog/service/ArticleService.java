package com.mxx910.blog.service;



import com.baomidou.mybatisplus.extension.service.IService;
import com.mxx910.blog.entity.Article;
import com.mxx910.blog.model.DTO.ArticleDTO;
import com.mxx910.blog.model.VO.ArchiveVO;
import com.mxx910.blog.model.VO.ArticleHomeVO;
import com.mxx910.blog.model.VO.ArticleVO;
import com.mxx910.blog.model.VO.PageResult;
import com.mxx910.blog.model.VO.ArticleRecommendVO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


/**
 * @author: mxx910
 * @date: 2023/5/5
 * @description:
 */
public interface ArticleService extends IService<Article> {
    /**
     *查看首页文章列表
     *
     * @return 首页文章列表
     */
    PageResult<ArticleHomeVO> listArticleHomeVO();

    ArticleVO getArticleHomeById(Integer articleId);

    PageResult<ArchiveVO> listArchiveVO();

    String saveArticleImages(MultipartFile file);

    int addArticle(ArticleDTO article);

    List<ArticleRecommendVO> listArticleRecommendVO();
}
