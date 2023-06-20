package com.mxx910.blog.strategy;

/**
 * @author: mxx910
 * @date: 2023/6/16
 * @description:
 */
public interface LikeStrategy {

    /**
     * 点赞
     *
     * @param typeId 类型id
     */
    void like(Integer typeId);
}

