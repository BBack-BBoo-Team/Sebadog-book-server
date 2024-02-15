package com.book.app.modules.global.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;

import java.lang.reflect.Method;

@Slf4j
@Aspect
public class ApiLogAop {

    @Pointcut("execution(* com.book.app.modules..controller..*(..))")
    private void cut() {}

    @Before("cut()")
    public void before(JoinPoint joinPoint) {
        Signature signature = joinPoint.getSignature();

        log.info("======= [START API] {} =======",
                signature.getName().toString());

        log.info("Controller = {}",
                signature.getDeclaringTypeName());

        // 파라미터 받아오기
        Object[] args = joinPoint.getArgs();
        if (args.length <= 0) log.info("no parameter");
        for (Object arg : args) {
            log.info("Parameter type = {}", arg.getClass().getSimpleName());
            log.info("Parameter value = {}", arg);
        }
    }

    /** DEBUG API LOGGING **/
    @Around("execution(* com.book.app.modules..*(..))")
    public Object execute(ProceedingJoinPoint joinPoint) throws Throwable {

        long startTime = System.currentTimeMillis();
        Signature signature = joinPoint.getSignature();

        log.debug("REQUEST : {}({})",
                signature.getDeclaringType(),
                signature.getName().toString());

        Object result = joinPoint.proceed();

        long endTime = System.currentTimeMillis();
        long timeMS = endTime - startTime;

        log.debug("RESPONSE : {}({}) = {} ({}ms)",
                signature.getDeclaringTypeName(),
                signature.getName(),
                result,
                timeMS);

        return result;
    }

    // Poincut에 의해 필터링된 경로로 들어오는 경우 메서드 리턴 후에 적용
    @AfterReturning(value = "cut()", returning = "returnObj")
    public void afterReturnLog(JoinPoint joinPoint, Object returnObj) {
        Method method = getMethod(joinPoint);
        log.info("======= [END API] {} =======", method.getName());
        log.info("Return type = {}", returnObj.getClass().getSimpleName());
        log.info("Return value = {}", returnObj);
    }

    // API 메서드 정보 가져오기
    private Method getMethod(JoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        return signature.getMethod();
    }

}
