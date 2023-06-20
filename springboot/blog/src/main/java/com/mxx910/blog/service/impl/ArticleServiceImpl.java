package com.mxx910.blog.service.impl;



import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.lang.Assert;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mxx910.blog.constant.ExceptionConstant;
import com.mxx910.blog.constant.RedisConstant;
import com.mxx910.blog.entity.*;
import com.mxx910.blog.enums.ArticleStatusEnum;
import com.mxx910.blog.exception.ServiceException;
import com.mxx910.blog.mapper.*;
import com.mxx910.blog.model.DTO.ArticleDTO;
import com.mxx910.blog.model.VO.*;
import com.mxx910.blog.service.ArticleService;
import com.mxx910.blog.service.RedisService;
import com.mxx910.blog.service.TagService;
import com.mxx910.blog.strategy.context.UploadStrategyContext;
import com.mxx910.blog.utils.BeanCopyUtils;
import com.mxx910.blog.utils.FileUtils;
import com.mxx910.blog.utils.PageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.mxx910.blog.constant.CommonConstant.FALSE;
import static com.mxx910.blog.constant.RedisConstant.SITE_SETTING;
import static com.mxx910.blog.enums.FilePathEnum.ARTICLE;


/**
 * @author: mxx910
 * @date: 2023/5/5
 * @description:
 */
@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements ArticleService  {
    @Autowired
    ArticleMapper articleMapper;

    @Autowired
    BlogFileMapper blogFileMapper;

    @Autowired
    RedisService redisService;

    @Autowired
    private UploadStrategyContext uploadStrategyContext;

    @Autowired
    private CategoryMapper categoryMapper;
    @Autowired
    private ArticleTagMapper articleTagMapper;
    @Autowired
    private TagMapper tagMapper;
    @Autowired
    private TagService tagService;


    @Override
    public PageResult<ArticleHomeVO> listArticleHomeVO() {
        Long count = articleMapper.selectCount(new LambdaQueryWrapper<Article>()
                .eq(Article::getIsDelete, FALSE)
                .eq(Article::getStatus, ArticleStatusEnum.PUBLIC.getStatus())
        );
        if (count == 0){
            return new PageResult<>();
        }
        List<ArticleHomeVO> articleHomeVOList = articleMapper.selectArticleHomeList(PageUtils.getLimit(), PageUtils.getSize());
        return new PageResult<>(articleHomeVOList,count);
    }

    @Override
    public ArticleVO getArticleHomeById(Integer articleId) {
        ArticleVO article = articleMapper.selectArticleHomeById(articleId);
        Assert.notNull(article, ExceptionConstant.RESOURCE_NOT_FOUND);
        redisService.incrZet(RedisConstant.ARTICLE_VIEW_COUNT, articleId, 1D);


        ArticlePaginationVO lastArticle = articleMapper.selectLastArticle(articleId);
        // 查询下一篇文章
        ArticlePaginationVO nextArticle = articleMapper.selectNextArticle(articleId);
        article.setLastArticle(lastArticle);
        article.setNextArticle(nextArticle);

        Double viewCount = Optional.ofNullable(redisService.getZsetScore(RedisConstant.ARTICLE_VIEW_COUNT, articleId))
                .orElse((double) 0);
        article.setViewCount(viewCount.intValue());
        // 查询点赞量
        Integer likeCount = redisService.getHash(RedisConstant.ARTICLE_LIKE_COUNT, articleId.toString());
        article.setLikeCount(Optional.ofNullable(likeCount).orElse(0));
        return article;


    }

    @Override
    public PageResult<ArchiveVO> listArchiveVO() {
        Long cnt = articleMapper.selectCount(new LambdaQueryWrapper<Article>().eq(Article::getIsDelete, FALSE).eq(Article::getStatus,ArticleStatusEnum.PUBLIC.getStatus()));
        if(cnt == 0){
            return new PageResult<>();
        }
        List<ArchiveVO> archiveVOList = articleMapper.selectArchiveList(PageUtils.getLimit(),PageUtils.getSize());
        return new PageResult<>(archiveVOList,cnt);
    }

    @Override
    public String saveArticleImages(MultipartFile file) {
        try{
            // 获取文件md5 作为文件名
            String md5 = FileUtils.getMd5(file.getInputStream());
            // 获取拓展名
            String extName = FileUtils.getExtension(file);
            BlogFile existFile = blogFileMapper.selectOne(new LambdaQueryWrapper<BlogFile>()
                    .select(BlogFile::getId).select(BlogFile::getFileUrl)
                    .eq(BlogFile::getFileName, md5)
                    .eq(BlogFile::getFilePath, ARTICLE.getFilePath()));
            if (!Objects.isNull(existFile)){
                return existFile.getFileUrl();
            }else{
                String url = uploadStrategyContext.executeUploadStrategy(file, ARTICLE.getPath());
                BlogFile newFile = BlogFile.builder()
                        .fileUrl(url)
                        .fileName(md5)
                        .filePath(ARTICLE.getFilePath())
                        .extendName(extName)
                        .fileSize((int) file.getSize())
                        .isDir(FALSE)
                        .build();
                blogFileMapper.insert(newFile);
                return url;
            }
        }catch (IOException e) {
            throw new ServiceException("文件上传失败");
        }
    }

    @Override
    public int addArticle(ArticleDTO article) {
// 保存文章分类
        Integer categoryId = saveArticleCategory(article);
        // 添加文章
        Article newArticle = BeanCopyUtils.copyBean(article, Article.class);
        if (StringUtils.isBlank(newArticle.getArticleCover())) {
            SiteConfig siteConfig = redisService.getObject(SITE_SETTING);
            newArticle.setArticleCover(siteConfig.getArticleCover());
        }
        newArticle.setCategoryId(categoryId);
        newArticle.setUserId(StpUtil.getLoginIdAsInt());
        baseMapper.insert(newArticle);
        // 保存文章标签
        saveArticleTag(article, newArticle.getId());
        return newArticle.getId();

    }

    @Override
    public List<ArticleRecommendVO> listArticleRecommendVO() {
        return articleMapper.selectArticleRecommend();
    }

    /**
     * 保存文章分类
     *
     * @param article 文章信息
     * @return 文章分类
     */
    private Integer saveArticleCategory(ArticleDTO article) {
        // 查询分类
        Category category = categoryMapper.selectOne(new LambdaQueryWrapper<Category>()
                .select(Category::getId)
                .eq(Category::getCategoryName, article.getCategoryName()));
        // 分类不存在
        if (Objects.isNull(category)) {
            category = Category.builder()
                    .categoryName(article.getCategoryName())
                    .build();
            // 保存分类
            categoryMapper.insert(category);
        }
        return category.getId();
    }
    /**
     * 保存文章标签
     *
     * @param article   文章信息
     * @param articleId 文章id
     */
    private void saveArticleTag(ArticleDTO article, Integer articleId) {
        // 删除文章标签
        articleTagMapper.delete(new LambdaQueryWrapper<ArticleTag>()
                .eq(ArticleTag::getArticleId, articleId));
        // 标签名列表
        List<String> tagNameList = article.getTagNameList();
        if (CollectionUtils.isNotEmpty(tagNameList)) {
            // 查询出已存在的标签
            List<Tag> existTagList = tagMapper.selectTagList(tagNameList);
            List<String> existTagNameList = existTagList.stream()
                    .map(Tag::getTagName)
                    .collect(Collectors.toList());
            List<Integer> existTagIdList = existTagList.stream()
                    .map(Tag::getId)
                    .collect(Collectors.toList());
            // 移除已存在的标签列表
            tagNameList.removeAll(existTagNameList);
            // 含有新标签
            if (CollectionUtils.isNotEmpty(tagNameList)) {
                // 新标签列表
                List<Tag> newTagList = tagNameList.stream()
                        .map(item -> Tag.builder()
                                .tagName(item)
                                .build())
                        .collect(Collectors.toList());
                // 批量保存新标签
                tagService.saveBatch(newTagList);
                // 获取新标签id列表
                List<Integer> newTagIdList = newTagList.stream()
                        .map(Tag::getId)
                        .collect(Collectors.toList());
                // 新标签id添加到id列表
                existTagIdList.addAll(newTagIdList);
            }
            // 将所有的标签绑定到文章标签关联表
            articleTagMapper.saveBatchArticleTag(articleId, existTagIdList);
        }
    }
}
