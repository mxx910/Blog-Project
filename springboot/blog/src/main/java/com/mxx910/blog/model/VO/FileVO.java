package com.mxx910.blog.model.VO;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author: mxx910
 * @date: 2023/6/14
 * @description:
 */
@Data
@ApiModel(description = "文件后台VO")
public class FileVO {
    /**
     * 文件id
     */
    @ApiModelProperty(value = "文件id")
    private Integer id;

    /**
     * 文件url
     */
    @ApiModelProperty(value = "文件url")
    private String fileUrl;

    /**
     * 文件名
     */
    @ApiModelProperty(value = "文件名")
    private String fileName;

    /**
     * 文件大小
     */
    @ApiModelProperty(value = "文件大小")
    private Integer fileSize;

    /**
     * 文件类型
     */
    @ApiModelProperty(value = "文件类型")
    private String extendName;

    /**
     * 文件路径
     */
    @ApiModelProperty(value = "文件路径")
    private String filePath;

    /**
     * 是否为目录 (0否 1是)
     */
    @ApiModelProperty(value = "是否为目录 (0否 1是)")
    private Integer isDir;

    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;
}
