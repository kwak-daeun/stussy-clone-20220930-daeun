package com.stussy.stussyclone20220930kde.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.CodeSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class LogAop {

    @Pointcut("execution(* com.stussy.stussyclone20220930kde.api.*Api.*(..))") //모든 메소드의 모든 매개변수, 매개변수가 있을때만 실행은 괄호 안에 .. 없을때는 괄호안에 *
    private void pointCut(){}

    @Pointcut("@annotation(com.stussy.stussyclone20220930kde.aop.annotation.LogAspect)")
    private void annotationPointCut(){}



    @Around("annotationPointCut()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {

        CodeSignature codeSignature = (CodeSignature) joinPoint.getSignature(); //codesigature로 다운캐스팅 가능
        String className = codeSignature.getDeclaringTypeName(); //클래스명
        String methodName = codeSignature.getName(); //메소드명
        String[] parameterNames = codeSignature.getParameterNames(); //파라미터 이름 들고옴,위에서 다운캐스팅 안하면 파라미터 못들감
        Object[] args = joinPoint.getArgs(); //파라미터 값 들고옴

        for(int i=0; i<parameterNames.length; i++) {
            log.info("<<<<Parameter info >>>> {}.{} >>>[{}: {}]", className, methodName, parameterNames[i], args[i]);
        }

        Object result = joinPoint.proceed();

        log.info("<<<<Return>>>> {}.{} >>>[{}]", className, methodName, result);

        return result;


    }
}
