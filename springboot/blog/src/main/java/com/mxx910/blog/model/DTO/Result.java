package com.mxx910.blog.model.DTO;

import com.mxx910.blog.enums.StatusCodeEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


/**
 * @author: mxx910
 * @date: 2023/4/19
 * @description:
 */
@Data
@ApiModel(description = "结果返回类")
public class Result<T> {
    /**
     * 返回状态
     */
    @ApiModelProperty(value = "返回状态")
    private Boolean flag;

    /**
     * 状态码
     */
    @ApiModelProperty(value = "状态码")
    private Integer code;

    /**
     * 返回信息
     */
    @ApiModelProperty(value = "返回信息")
    private String msg;

    /**
     * 返回数据
     */
    @ApiModelProperty(value = "返回数据")
    private T data;

    public static <T> Result<T> success() {
        return buildResult(true, null, StatusCodeEnum.SUCCESS.getCode(), StatusCodeEnum.SUCCESS.getMsg());
    }
    public static <T> Result<T> success(T data) {
        return buildResult(true, data, StatusCodeEnum.SUCCESS.getCode(), StatusCodeEnum.SUCCESS.getMsg());
    }

    public static <T> Result<T> fail(String message) {
        return buildResult(false, null,StatusCodeEnum.FAIL.getCode(), message);
    }

    public static <T> Result<T> fail(Integer code, String message) {
        return buildResult(false, null, code, message);
    }


    private static <T> Result<T> buildResult(Boolean flag, T data, Integer code, String message) {
        Result<T> r = new Result<>();
        r.setFlag(flag);
        r.setData(data);
        r.setCode(code);
        r.setMsg(message);
        return r;
    }

}
