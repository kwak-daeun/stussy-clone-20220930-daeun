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

    @Pointcut("execution(* com.stussy.stussyclone20220930kde..*Api.*(..))") //
    private void executionPointCut(){}

        @Around("executionPointCut()")
        public Object around(ProceedingJoinPoint joinPoint) throws Throwable {

        Object[] args = joinPoint.getArgs();
        //매개변수가 안에 다들어있는것 arguesment

            BeanPropertyBindingResult bindingResult = null;

            for(Object arg : args){
                System.out.println(arg);
                if(arg.getClass() == BeanPropertyBindingResult.class){
                 bindingResult = (BeanPropertyBindingResult) arg;
                 break;

                 }
            }


             if(bindingResult.hasErrors()){
                Map<String, String> errorMap = new HashMap<String, String>();

                List<FieldError> fieldErrors =bindingResult.getFieldErrors();
                for(FieldError fieldError : fieldErrors){
                    System.out.println("필드명: " +fieldError.getField());
                    System.out.println("에러 메세지: " +fieldError.getDefaultMessage());
                    errorMap.put(fieldError.getField(), fieldError.getDefaultMessage());

                }

                throw new CustomValidationException("Validation Error", errorMap); //강제로 예외를 발생 ""안에 메소드

                //return ResponseEntity.badRequest().body(errorMap);

            }

            Object result= null;
            result= joinPoint.proceed(); //이 메소드를 기준으로 위에 전, 밑에 후 처리로 나뉜다

        return result;

    }
}
