package com.stussy.stussyclone20220930kde.service;

import com.stussy.stussyclone20220930kde.Exception.CustomInternalServerErrorException;
import com.stussy.stussyclone20220930kde.Exception.CustomValidationException;
import com.stussy.stussyclone20220930kde.domain.User;
import com.stussy.stussyclone20220930kde.dto.RegisterReqDto;
import com.stussy.stussyclone20220930kde.dto.vaildation.ValidationGroups;
import com.stussy.stussyclone20220930kde.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService{

    private final AccountRepository accountRepository;

    @Override
    public void duplicateEmail(RegisterReqDto registerReqDto) throws Exception {
        User user = accountRepository.findUserByEmail(registerReqDto.getEmail());
        //User는 entity

        //이메일 중복확인
        if(user !=null){
            Map<String, String> errorMap = new HashMap<String, String>();
            errorMap.put("email", "이미 사용중인 이메일 주소입니다.");

            throw new CustomValidationException("Duplicate email", errorMap);
            //앞에 exceptionhandler 예외가 발생하면 customvalidtionException 일어난다

        }

    }

    //예외가 일어나지 않으면 , 그다음엔 회원가입 진행
    @Override
    public void register(RegisterReqDto registerReqDto) throws Exception {

        User user = registerReqDto.toEntity();
        int result = accountRepository.saveUser(user);
        if(result ==0){ //DB가 안들어갔으면
            throw new CustomInternalServerErrorException("회원가입 중 문제가 발생하였습니다.");//500에러가 뜨면발생한다.

        }

    }
}
