package com.stussy.stussyclone20220930kde.aop;

import com.stussy.stussyclone20220930kde.Exception.CustomValidationException;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.boot.context.properties.bind.BindResult;
import org.springframework.stereotype.Component;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.FieldError;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Aspect
@Component
public class ValidationAop {

    //@Pointcut("execution(* com.stussy.stussyclone20220930kde..*Api.*(..))")
    //*모든 메소드에 적용해라. get* get으로 시작하는 메소드 적용, set* set으로 시작 하는,  *.*는 모든 클래스
    // *api.* api로 끝나는 클래스 모두
    // 패키지는 지우고 ..으로 하면 하위 모든 패키지 적용.
    //private void executionPointCut(){}

    @Pointcut("@annotation(com.stussy.stussyclone20220930kde.aop.annotation.ValidAspect)")
    private void annotationPointCut(){}

        @Around("annotationPointCut()")
        public Object around(ProceedingJoinPoint joinPoint) throws Throwable {

        Object[] args = joinPoint.getArgs();
        //매개변수가 안에 다들어있는것 getarguesment

            BeanPropertyBindingResult bindingResult = null;

            //binding 객체가 들어있는거를 가져와야함으로 반복돌림
            for(Object arg : args){

                if(arg.getClass() == BeanPropertyBindingResult.class){
                    //상위객체 beenproperty를 붙여줘야함
                 bindingResult = (BeanPropertyBindingResult) arg;
                 break;

                 }
            }


             if(bindingResult.hasErrors()){
                Map<String, String> errorMap = new HashMap<String, String>();

                List<FieldError> fieldErrors =bindingResult.getFieldErrors();
                for(FieldError fieldError : fieldErrors){


                }

                throw new CustomValidationException("Validation Error", errorMap); //강제로 예외를 발생 ""안에 메소드

            }

            Object result= null;
            result= joinPoint.proceed(); //이 메소드를 기준으로 위에 전, 밑에 후 처리로 나뉜다

        return result;

    }
}
