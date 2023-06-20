package com.mxx910.blog.service;

import com.mxx910.blog.model.VO.BlogBackInfoVO;
import com.mxx910.blog.model.VO.BlogInfoVO;

public interface BlogInfoService {
    /**
     * 上传访客信息
     */
    void report();
    /**
     * 查看博客信息
     *
     * @return 博客信息
     */
    BlogInfoVO getBlogInfo();

    /**
     * 查看博客后台信息
     *
     * @return 博客后台信息
     */
//    BlogBackInfoVO getBlogBackInfo();

    /**
     * 查看关于我信息
     *
     * @return 关于我信息
     */
    String getAbout();

    /**
     * 查看博客后台信息
     *
     * @return 博客后台信息
     */
    BlogBackInfoVO getBlogBackInfo();
}
