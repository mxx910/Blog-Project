package com.mxx910.blog.model.DTO;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author: mxx910
 * @date: 2023/6/16
 * @description:
 */
@Data
@ApiModel(description = "定时任务运行")
public class TaskRunDTO {

    /**
     * 任务id
     */
    @ApiModelProperty(value = "任务id")
    private Integer id;

    /**
     * 任务组别
     */
    @ApiModelProperty(value = "任务组别")
    private String taskGroup;
}
