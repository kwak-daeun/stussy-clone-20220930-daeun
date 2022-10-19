package com.stussy.stussyclone20220930kde.service;

import com.stussy.stussyclone20220930kde.Exception.CustomInternalServerErrorException;
import com.stussy.stussyclone20220930kde.domain.User;
import com.stussy.stussyclone20220930kde.repository.AccountRepository;
import com.stussy.stussyclone20220930kde.security.PrincipalDetails;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class PrincipalDetailService implements UserDetailsService {

    private final AccountRepository accountRepository;



    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        User user =  null;

        try {
           user =  accountRepository.findUserByEmail(email);
        } catch (Exception e) {
            e.printStackTrace();
            throw new CustomInternalServerErrorException("회원 정보 조회 오류");
        }
        if(user == null) {
            throw new UsernameNotFoundException("잘 못된 사용자 정보"); //이 예외가 발생했을 때, 밑에 catch를 받지않는다.
           // 이 예외가 터지면 상속되어져있는 AuthenticationException에 upcasting되어 들어옴
        }
        return new PrincipalDetails(user); //db>userdails> return에 가져와서 비밀번호등 security 검증을 해준뒤 session에 넣음
    }
}
