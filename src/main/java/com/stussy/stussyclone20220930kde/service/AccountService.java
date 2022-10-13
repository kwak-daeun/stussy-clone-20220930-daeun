package com.stussy.stussyclone20220930kde.service;


import com.stussy.stussyclone20220930kde.dto.RegisterReqDto;

public interface AccountService {

    public void duplicateEmail(RegisterReqDto registerReqDto) throws Exception;
    public void register(RegisterReqDto registerReqDto) throws Exception;
    //회원가입만 진행하는것


}
