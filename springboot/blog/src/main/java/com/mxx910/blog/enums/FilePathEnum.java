package com.mxx910.blog.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author: mxx910
 * @date: 2023/6/14
 * @description: 文件路径枚举
 */
@Getter
@AllArgsConstructor
public enum FilePathEnum {

    /**
     * 头像路径
     */
    AVATAR("/avatar/", "/avatar", "头像路径"),

    /**
     * 文章图片路径
     */
    ARTICLE("/article/", "/article", "文章图片路径"),

    /**
     * 配置图片路径
     */
    CONFIG("/config/", "/config", "配置图片路径"),

    /**
     * 说说图片路径
     */
    TALK("/talk/", "/talk", "说说图片路径"),

    /**
     * 照片路径
     */
    PHOTO("/photo/", "/photo", "相册路径");

    /**
     * 路径
     */
    private final String path;

    /**
     * 文件路径
     */
    private final String filePath;

    /**
     * 描述
     */
    private final String description;
}
