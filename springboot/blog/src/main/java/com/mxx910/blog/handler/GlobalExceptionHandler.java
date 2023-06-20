package com.mxx910.blog.handler;

import cn.dev33.satoken.exception.DisableServiceException;
import cn.dev33.satoken.exception.NotLoginException;
import cn.dev33.satoken.exception.NotPermissionException;
import cn.dev33.satoken.exception.NotRoleException;
import com.mxx910.blog.constant.ExceptionConstant;
import com.mxx910.blog.enums.StatusCodeEnum;
import com.mxx910.blog.exception.ServiceException;
import com.mxx910.blog.model.DTO.Result;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Objects;

/**
 * @author: mxx910
 * @date: 2023/4/23
 * @description:
 */
Z
