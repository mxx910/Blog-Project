package com.mxx910.blog.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mxx910.blog.entity.Photo;
import com.mxx910.blog.model.DTO.ConditionDTO;

import java.util.Map;

public interface PhotoService extends IService<Photo> {

    /**
     * 查看照片列表
     *
     * @param condition 条件
     * @return 照片列表
     */
    Map<String, Object> listPhotoVO(ConditionDTO condition);
}
