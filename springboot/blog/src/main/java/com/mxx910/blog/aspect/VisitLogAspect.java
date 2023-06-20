package com.mxx910.blog.aspect;

import com.mxx910.blog.annotation.VisitLogger;
import com.mxx910.blog.entity.VisitLog;
import com.mxx910.blog.manager.AsyncManager;
import com.mxx910.blog.manager.factory.AsyncFactory;
import com.mxx910.blog.utils.IpUtils;
import com.mxx910.blog.utils.UserAgentUtils;
import jakarta.servlet.http.HttpServletRequest;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.Objects;

/**
 * @author: mxx910
 * @date: 2023/6/20
 * @description:
 */
@Aspect
@Component
public class VisitLogAspect {

    @Pointcut("@annotation(com.mxx910.blog.annotation.VisitLogger)")
    public void visitLogPointCut() {
    }

    /**
     * 连接点正常返回通知，拦截用户操作日志，正常执行完成后执行， 如果连接点抛出异常，则不会执行
     *
     * @param joinPoint 切面方法的信息
     * @param result    返回结果
     */
    @AfterReturning(value = "visitLogPointCut()", returning = "result")
    public void doAfterReturning(JoinPoint joinPoint, Object result) {
        // 从切面织入点处通过反射机制获取织入点处的方法
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        // 获取切入点所在的方法
        Method method = signature.getMethod();
        // 获取操作
        VisitLogger visitLogger = method.getAnnotation(VisitLogger.class);
        // 获取request
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = Objects.requireNonNull(attributes).getRequest();
        VisitLog visitLog = new VisitLog();
        String ipAddress = IpUtils.getIpAddress(request);
        String ipSource = IpUtils.getIpSource(ipAddress);
        // 解析browser和os
        Map<String, String> userAgentMap = UserAgentUtils.parseOsAndBrowser(request.getHeader("User-Agent"));
        visitLog.setIpAddress(ipAddress);
        visitLog.setIpSource(ipSource);
        visitLog.setOs(userAgentMap.get("os"));
        visitLog.setBrowser(userAgentMap.get("browser"));
        visitLog.setPage(visitLogger.value());
        // 保存到数据库
        AsyncManager.getInstance().execute(AsyncFactory.recordVisit(visitLog));
    }

}
