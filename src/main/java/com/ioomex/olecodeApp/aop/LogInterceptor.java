package com.ioomex.olecodeApp.aop;

import java.lang.reflect.AnnotatedElement;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;
import javax.servlet.http.HttpServletRequest;

import com.ioomex.olecodeApp.annotation.OperationLog;
import com.ioomex.olecodeApp.mapper.SysOperationLogMapper;
import com.ioomex.olecodeApp.model.dto.builder.SysOperationLogBuilder;
import com.ioomex.olecodeApp.model.entity.SysOperationLog;
import com.ioomex.olecodeApp.utils.IpAddressUtils;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StopWatch;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * 请求响应日志 AOP
 *
 * @author ioomex
 * @from <a href="https://github.com/yangwan-cw">yangwan-cw仓库</a>
 **/
@Aspect
@Component
@Slf4j
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class)
public class LogInterceptor {

    private final SysOperationLogMapper sysOperationLogMapper;

    /**
     * 环绕通知，拦截带有 @OperationLog 注解的方法。
     * 记录请求细节，执行方法，记录响应细节，并保存操作日志。
     *
     * @param point        ProceedingJoinPoint，被拦截方法的连接点
     * @param operationLog OperationLog 注解实例
     * @return 被拦截方法的执行结果
     * @throws Throwable 方法执行过程中可能抛出的异常
     */
    @Around("execution(* com.ioomex.olecodeApp.controller.*.*(..))")
    public Object doInterceptor(ProceedingJoinPoint point) {
        // 计时器，用于统计方法执行时间
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        // 获取请求信息
        RequestAttributes requestAttributes = RequestContextHolder.currentRequestAttributes();
        HttpServletRequest httpServletRequest = ((ServletRequestAttributes) requestAttributes).getRequest();

        // 生成唯一请求 ID 和获取请求 URL
        String requestId = UUID.randomUUID().toString();
        String url = httpServletRequest.getRequestURI();

        // 获取方法上的注解值
        String operationName = getOperationName(point);

        // 记录请求细节
        logRequestDetails(requestId, url, httpServletRequest, point.getArgs());

        // 执行原始方法
        Object result = null;
        Throwable errorLog = null;
        try {
            result = point.proceed();
        } catch (Throwable e) {
            errorLog = e;
            throw new RuntimeException(e);
        } finally {
            // 停止计时
            stopWatch.stop();

            // 记录响应细节
            logResponseDetails(requestId, stopWatch.getTotalTimeMillis());

            // 记录响应日志和保存操作日志到数据库
            this.logOperation(point, operationName, result, errorLog, stopWatch, requestId, url);
        }

        return result;
    }

    /**
     * 获取方法上的操作名称，优先从 @OperationLog 注解获取，然后从 @ApiOperation 注解获取。
     *
     * @param point ProceedingJoinPoint，被拦截方法的连接点
     * @return      方法的操作名称
     */
    private String getOperationName(ProceedingJoinPoint point) {
        // 获取方法签名
        MethodSignature methodSignature = (MethodSignature) point.getSignature();

        // 尝试从方法上获取 @OperationLog 注解
        OperationLog operationLog = methodSignature.getMethod().getAnnotation(OperationLog.class);
        if (operationLog != null) {
            return operationLog.log();
        }

        // 尝试从方法上获取 @ApiOperation 注解
        ApiOperation apiOperation = methodSignature.getMethod().getAnnotation(ApiOperation.class);
        if (apiOperation != null) {
            return apiOperation.value();
        }

        // 如果都没有找到，则返回默认值
        return "未命名操作";
    }


    /**
     * 记录请求的详细信息，包括请求 ID、请求路径、IP 地址和请求参数。
     *
     * @param requestId          请求的唯一 ID
     * @param url                请求的 URL 路径
     * @param httpServletRequest HTTP 请求对象
     * @param args               方法参数数组
     */
    private void logRequestDetails(String requestId, String url, HttpServletRequest httpServletRequest, Object[] args) {
        log.info("请求开始，ID: {}", requestId);
        log.info("请求路径: {}", url);
        log.info("IP地址: {}", httpServletRequest.getRemoteHost());
        log.info("请求参数: {}", "[" + StringUtils.join(args, ", ") + "]");
    }

    /**
     * 记录响应的详细信息，包括请求 ID 和请求耗时。
     *
     * @param requestId       请求的唯一 ID
     * @param totalTimeMillis 请求处理总耗时（毫秒）
     */
    private void logResponseDetails(String requestId, long totalTimeMillis) {
        log.info("请求结束，ID: {}, 请求耗时: {}ms", requestId, totalTimeMillis);
    }

    /**
     * 记录操作日志，并将其保存到数据库。
     *
     * @param point        ProceedingJoinPoint，被拦截方法的连接点
     * @param operationLog OperationLog 注解实例
     * @param result       方法执行结果
     * @param errorLog     方法执行过程中可能抛出的异常
     * @param stopWatch    StopWatch，用于统计方法执行时间
     * @param requestId    请求的唯一 ID
     * @param url          请求的 URL 路径
     */
    @Async
    public void logOperation(ProceedingJoinPoint point,
                              String operationLog,
                              Object result,
                              Throwable errorLog,
                              StopWatch stopWatch,
                              String requestId,
                              String url) {
        // 构建操作日志对象
        SysOperationLog sysOperationLog = new SysOperationLogBuilder()
          .point(point)
          .operationLog(operationLog)
          .result(result)
          .errorLog(errorLog)
          .stopWatch(stopWatch)
          .requestId(requestId)
          .url(url)
          .build();

        // 保存操作日志到数据库
        sysOperationLogMapper.insert(sysOperationLog);
    }
}

