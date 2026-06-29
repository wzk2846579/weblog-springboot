package cn.wzk.weblog.common.aspect;

import cn.wzk.weblog.common.util.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * @author wangzk16
 * @date 2026年06月23日 15:11
 */
@Aspect
@Component
@Slf4j
public class ApiOperationLogAspect {

    @Pointcut("@annotation(cn.wzk.weblog.common.aspect.ApiOperationLog)")
    public void apiOperationLog() {}

    @Pointcut("args(id)")
    public void getById(Long id) {}

    @Around("apiOperationLog()")
    public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable {
        try {
            MDC.put("traceId", UUID.randomUUID().toString());

            // 获取请求的类名
            String className = joinPoint.getTarget().getClass().getCanonicalName();
            // 获取请求的方法签名
            MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
            // 获取请求的方法名
            String methodName = methodSignature.getName();

            // 获取方法上标注的ApiOperationLog注解
            ApiOperationLog apiOperationLog = methodSignature.getMethod().getAnnotation(ApiOperationLog.class);
            // 通过注解获取API功能描述
            String description = apiOperationLog.description();

            // 获取方法入参
            Object[] args = joinPoint.getArgs();
            // 将方法入参全部转成JSON字符串
            String argsJsonStr = Arrays.asList(args).stream().map(arg -> JsonUtil.toJsonString(arg)).collect(Collectors.joining(", "));

            // 打印入参等信息
            log.info("====== 请求开始：[{}]，请求类：{}，请求方法：{}，入参：{} ======", description, className, methodName, argsJsonStr);

            // 请求开始时间
            Long startTime = System.currentTimeMillis();
            // 执行切点方法
            Object result = joinPoint.proceed();
            // 方法执行耗时
            Long executeTime = System.currentTimeMillis() - startTime;

            // 打印出参等信息
            log.info("====== 请求结束：[{}]，耗时：{}ms，出参：{} ======", description, executeTime, JsonUtil.toJsonString(result));

            return result;
        } finally {
            MDC.clear();
        }
    }

    @Before("getById(id)")
    public void doBefore(Long id) {
        log.info("*#*#*#*#*#*#*#");
    }
}
