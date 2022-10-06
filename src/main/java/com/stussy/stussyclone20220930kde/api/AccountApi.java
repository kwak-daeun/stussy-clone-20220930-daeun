package com.stussy.stussyclone20220930kde.api;

import com.stussy.stussyclone20220930kde.Exception.CustomValidationException;
import com.stussy.stussyclone20220930kde.dto.CMRespDto;
import com.stussy.stussyclone20220930kde.dto.RegisterReqDto;
import com.stussy.stussyclone20220930kde.dto.vaildation.ValidationSequence;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.sql.SQLOutput;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequestMapping("/api/account")/*괄호 안에 mapping주소*/
@RestController
/*responsebody 가지고있는 controller 무조건 데이터 view는 안됨*/
public class AccountApi {

    @PostMapping("/register")
    public ResponseEntity<?> register(@Validated(ValidationSequence.class) @RequestBody RegisterReqDto registerReqDto, BindingResult bindingResult){
        //body데이터를 넘기는것 무조건 json dto

        return ResponseEntity.created(null).body(new CMRespDto<> ("회원가입성공" , registerReqDto));

    }
}
