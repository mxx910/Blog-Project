package com.mxx910.blog.exception;

import com.mxx910.blog.enums.StatusCodeEnum;
import lombok.Getter;

/**
 * @author: mxx910
 * @date: 2023/4/19
 * @description:
 */
@Getter
public class ServiceException extends RuntimeException{
    private final Integer code = StatusCodeEnum.FAIL.getCode();
    private final String message;
    public ServiceException(String message) {
        this.message = message;
    }

}
