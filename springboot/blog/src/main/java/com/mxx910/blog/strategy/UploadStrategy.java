package com.mxx910.blog.strategy;

import org.springframework.web.multipart.MultipartFile;

/**
 * 上传策略
 *
 * @author mxx910
 */
public interface UploadStrategy {
    /**
     * 上传文件
     *
     * @param file 文件
     * @param path 上传路径
     * @return {@link String} 文件地址
     */
    String uploadFile(MultipartFile file, String path);
}
