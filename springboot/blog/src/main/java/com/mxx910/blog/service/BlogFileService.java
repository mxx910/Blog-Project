package com.mxx910.blog.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mxx910.blog.entity.BlogFile;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author mxx910
 */
public interface BlogFileService extends IService<BlogFile> {
    /**
     * 上传图片文件
     * @param file 文件
     * @param path 文件路径
     */
    void uploadFile(MultipartFile file, String path);
}
