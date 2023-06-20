package com.mxx910.blog.model.VO;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author: mxx910
 * @date: 2023/4/24
 * @description:
 */
@Data
@ApiModel(description = "留言后台VO")
public class MessageBackVO {

    /**
     * 留言id
     */
    @ApiModelProperty(value = "留言id")
    private Integer id;

    /**
     * 昵称
     */
    @ApiModelProperty(value = "昵称")
    private String nickname;

    /**
     * 头像
     */
    @ApiModelProperty(value = "头像")
    private String avatar;

    /**
     * 留言内容
     */
    @ApiModelProperty(value = "留言内容")
    private String messageContent;

    /**
     * 用户ip
     */
    @ApiModelProperty(value = "用户ip")
    private String ipAddress;

    /**
     * 用户地址
     */
    @ApiModelProperty(value = "用户地址")
    private String ipSource;

    /**
     * 是否通过 (0否 1是)
     */
    @ApiModelProperty(value = "是否通过 (0否 1是)")
    private Integer isCheck;

    /**
     * 留言时间
     */
    @ApiModelProperty(value = "留言时间")
    private LocalDateTime createTime;
}
