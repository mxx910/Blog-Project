package com.mxx910.blog.model.VO;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author: mxx910
 * @date: 2023/4/24
 * @description:
 */
@Data
@ApiModel(description = "分类选项VO")
public class CategoryOptionVO {

    /**
     * 分类id
     */
    @ApiModelProperty(value = "分类id")
    private Integer id;

    /**
     * 分类名
     */
    @ApiModelProperty(value = "分类名")
    private String categoryName;
}
