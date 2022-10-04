package com.stussy.stussyclone20220930kde.api;

import com.stussy.stussyclone20220930kde.dto.RegisterReqDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/account")
@RestController
/*responsebody 가지고있는 controller*/
public class AccountApi {

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterReqDto registerReqDto){
        System.out.println("회원가입 요청 데이터: " + registerReqDto);
        return null;
    }
}
