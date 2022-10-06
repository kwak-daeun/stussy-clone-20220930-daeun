package com.stussy.stussyclone20220930kde.Exception;

import lombok.Getter;

import java.util.Map;

@Getter
public class CustomValidationException extends RuntimeException{

    private Map<String, String> errorMap;

    public CustomValidationException(String message, Map<String, String> errorMap){ //생성자
        super(message); //super는 runtimeexception 생성자
        this.errorMap = errorMap;
    }
}
