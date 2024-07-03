package com.ioomex.olecodeApp.model.dto.builder;
import com.ioomex.olecodeApp.model.entity.SysOperationLog;
import com.ioomex.olecodeApp.utils.IpAddressUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.util.StopWatch;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.time.LocalDateTime;
import java.util.Objects;

public class SysOperationLogBuilder {
    private ProceedingJoinPoint point;
    private String operationLog;
    private Object result;
    private Throwable errorLog;
    private StopWatch stopWatch;
    private String requestId;
    private String url;

    public SysOperationLogBuilder point(ProceedingJoinPoint point) {
        this.point = point;
        return this;
    }

    public SysOperationLogBuilder operationLog(String operationLog) {
        this.operationLog = operationLog;
        return this;
    }

    public SysOperationLogBuilder result(Object result) {
        this.result = result;
        return this;
    }

    public SysOperationLogBuilder errorLog(Throwable errorLog) {
        this.errorLog = errorLog;
        return this;
    }

    public SysOperationLogBuilder stopWatch(StopWatch stopWatch) {
        this.stopWatch = stopWatch;
        return this;
    }

    public SysOperationLogBuilder requestId(String requestId) {
        this.requestId = requestId;
        return this;
    }

    public SysOperationLogBuilder url(String url) {
        this.url = url;
        return this;
    }

    public SysOperationLog build() {
        String packName = point.getTarget().getClass().getName() + "." + point.getSignature().getName();
        String ip = ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest().getRemoteAddr();
        String region = IpAddressUtils.getRegion(ip);
        SysOperationLog sysOperationLog = new SysOperationLog();
        sysOperationLog.setLog(operationLog);
        sysOperationLog.setOnlyid(requestId);
        sysOperationLog.setApiAddress(url);
        sysOperationLog.setPackagePath(packName);
        sysOperationLog.setOperIp(ip);
        sysOperationLog.setParamJson(StringUtils.join(point.getArgs(), ","));
        sysOperationLog.setResultJson(ObjectUtils.isNotEmpty(result)? result.toString() : null);
        sysOperationLog.setCostTime(String.valueOf(stopWatch.getTotalTimeMillis()));
        sysOperationLog.setOperIpAddress(region);
        LocalDateTime now = LocalDateTime.now();
        sysOperationLog.setOperTime(now);
        if (ObjectUtils.isNotEmpty(errorLog)) {
            sysOperationLog.setStatus((short) 0);
            sysOperationLog.setErrorMsg(errorLog.toString());
        }
        return sysOperationLog;
    }
}
