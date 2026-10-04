package com.mik.common.event.aop.aspects;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;

import java.util.Arrays;
import java.util.List;

@Slf4j
@Aspect
public class ServiceLoggingCommonAspect {

    @PostConstruct
    public void init() {
        log.info("Initializing Aspect for service");
    }

    //Before
    @Before("@annotation(com.mik.common.event.aop.annotations.ServiceBeforeTracking)")
    public void before(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        List<Object> arguments = Arrays.asList(joinPoint.getArgs());
        log.info("Call method: {} Arguments: {}", methodName, arguments);
    }

    //AfterThrowing
    @AfterThrowing(value = "@annotation(com.mik.common.event.aop.annotations.ServiceAfterThrowingTracking)",throwing = "throwable")
    public void afterThrowing(JoinPoint joinPoint, Throwable throwable) {
        String nameMethod = joinPoint.getSignature().getName();
        List<Object> argsMethod = Arrays.asList(joinPoint.getArgs());
        List<StackTraceElement> stackTraceElementsMethod = Arrays.asList(throwable.getStackTrace());
        log.error("Ошибка в работе метода: {} Параметры метода: {} Код ошибки: {}",
                nameMethod,argsMethod,stackTraceElementsMethod);
        log.error("Exception: {}", stackTraceElementsMethod);
    }

    //AfterReturning
    @AfterReturning(value = "@annotation(com.mik.common.event.aop.annotations.ServiceAfterReturningTracking)",returning = "value")
    public void afterReturning(JoinPoint joinPoint,Object value) {
        String nameMethod = joinPoint.getSignature().getName();
        List<Object> argsMethod = Arrays.asList(joinPoint.getArgs());
        log.info("Выполнение метода: {} Параметры метода: {} Результат выполнение метода: {}",
                nameMethod,argsMethod,value);
    }

    @AfterReturning(value = "@annotation(com.mik.common.event.aop.annotations.ServiceAfterReturningTracking)",returning = "values")
    public void afterReturning(JoinPoint joinPoint,List<Object> values) {
        String nameMethod = joinPoint.getSignature().getName();
        List<Object> argsMethod = Arrays.asList(joinPoint.getArgs());
        log.info("Вызов метода: {} Параметры метода: {} Результат выполнения метода (список): {}",nameMethod,argsMethod,values);
    }

    //Around
    @Around("@annotation(com.mik.common.event.aop.annotations.ServiceAroundTracking)")
    public Object around(ProceedingJoinPoint joinPoint) {
        Object result = null;
        String methodName = joinPoint.getSignature().getName();
        List<Object> arguments = Arrays.asList(joinPoint.getArgs());
        try {
            log.warn("Вызываем метод: {} с параметрами: {}", methodName, arguments);
            result = joinPoint.proceed(arguments.toArray());
            log.info("Результат выполнения метода: {} с параметрами: {} Результат работы: {}", methodName, arguments, result);
        } catch (Throwable throwable) {
            log.error("Произошла ошибка в работе метода: {} с параметрами: {} Код ошибки: {}", methodName, arguments, throwable.getStackTrace());
        } finally {
            return result;
        }
    }





}
