package com.mxx910.blog.interceptor;

import com.alibaba.fastjson2.JSON;
import com.mxx910.blog.model.DTO.Result;
import com.mxx910.blog.annotation.AccessLimit;
import com.mxx910.blog.service.RedisService;
import com.mxx910.blog.utils.IpUtils;
import com.mxx910.blog.utils.WebUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.NotNull;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.RedisConnectionFailureException;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import java.util.concurrent.TimeUnit;


import java.util.Objects;

/**
 * @author: mxx910
 * @date: 2023/4/19
 * @description:
 */
@Slf4j
@Component
public class AccessLimitInterceptor implements HandlerInterceptor {
    @Autowired
    private RedisService redisService;
    @Override
    public boolean preHandle(@NotNull HttpServletRequest request, @NotNull HttpServletResponse response, @NotNull Object handler){
        boolean result = true;
        if (handler instanceof HandlerMethod handlerMethod){
            AccessLimit accessLimit = handlerMethod.getMethodAnnotation(AccessLimit.class);
            if (accessLimit != null){
                int seconds = accessLimit.seconds();
                int maxCount = accessLimit.maxCount();
                String ip = IpUtils.getIpAddress(request);
                String method = request.getMethod();
                String requestUri = request.getRequestURI();
                String redisKey = ip +":" + method +":"+requestUri;
                try{
                    Long count = redisService.incr(redisKey, 1L);
                    if (Objects.nonNull(count) && count == 1) {
                        redisService.setExpire(redisKey, seconds, TimeUnit.SECONDS);
                    }else if(count > maxCount){
                        WebUtils.renderString(response, JSON.toJSONString(Result.fail(accessLimit.msg())));
                        log.warn(redisKey + "请求次数超过每" + seconds + "秒" + maxCount + "次");
                        result = false;
                    }
                }catch (RedisConnectionFailureException e){
                    log.error("redis错误: " + e.getMessage());
                    result = false;
                }
            }
        }
        return result;
    }
}
