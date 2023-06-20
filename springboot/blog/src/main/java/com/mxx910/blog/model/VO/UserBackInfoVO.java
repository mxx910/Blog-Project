package com.mxx910.blog.model.VO;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * @author: mxx910
 * @date: 2023/6/14
 * @description:
 */
@Data
@Builder
@ApiModel(description = "后台登录用户信息")
public class UserBackInfoVO {

    /**
     * 用户id
     */
    @ApiModelProperty(value = "用户id")
    private Integer id;

    /**
     * 头像
     */
    @ApiModelProperty(value = "头像")
    private String avatar;

    /**
     * 角色
     */
    @ApiModelProperty(value = "角色")
    private List<String> roleList;

    /**
     * 权限标识
     */
    @ApiModelProperty(value = "权限标识")
    private List<String> permissionList;

}
