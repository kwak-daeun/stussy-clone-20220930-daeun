package com.stussy.stussyclone20220930kde.api.advice;

import com.stussy.stussyclone20220930kde.Exception.CustomValidationException;
import com.stussy.stussyclone20220930kde.dto.CMRespDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestController
@RestControllerAdvice
public class RestControllerExceptionHandler {

    @ExceptionHandler(CustomValidationException.class) //이 클래스 예외가 일어났다 핸들러로 잡아줌
    public ResponseEntity<?> validationErrorException(CustomValidationException e){ // 예외를 던져줌 e변수 생김
        return ResponseEntity.badRequest().body(new CMRespDto<>(e.getMessage(), e.getErrorMap()));
            //body에 CMRespDto 객체 넣으면 json으로 날라간다 > key:value 값
            //badRequest, getMessage, getErrorMap 만 바뀌고 형식 그대로임
    }
}
