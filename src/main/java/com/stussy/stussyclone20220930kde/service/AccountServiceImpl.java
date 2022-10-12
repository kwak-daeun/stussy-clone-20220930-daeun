package com.stussy.stussyclone20220930kde.service;

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
    public void register(RegisterReqDto registerReqDto) throws Exception {

        User user = accountRepository.findUserByEmail(registerReqDto.getEmail());

        if(user !=null){
            Map<String, String> errorMap = new HashMap<String, String>();
            errorMap.put("email", "이미 사용중인 이메일 주소입니다.");

            throw new CustomValidationException("Duplicate email", errorMap);
        }


    }
}
