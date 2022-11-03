package com.stussy.stussyclone20220930kde.service;

import com.stussy.stussyclone20220930kde.domain.User;
import com.stussy.stussyclone20220930kde.repository.AccountRepository;
import com.stussy.stussyclone20220930kde.security.PrincipalDetails;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Map;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class PrincipalOauth2Service extends DefaultOAuth2UserService {

    private final AccountRepository accountRepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = super.loadUser(userRequest);

        log.info("oAuth2User: {}", oAuth2User.getAttributes());
        log.info("userRequest: {}", userRequest.getClientRegistration());
        String provider = userRequest.getClientRegistration().getClientName(); //대문자로 시작하는 구글이나 네이버를 가져옴
        PrincipalDetails principalDetails = null;
        try {
           principalDetails = getPrincipalDetails(provider, oAuth2User.getAttributes());
        } catch (Exception e) {
            throw new OAuth2AuthenticationException("login failed");
        }

        return principalDetails;
    }

    private PrincipalDetails getPrincipalDetails(String provider, Map<String, Object> attributes) throws Exception {
        User user = null;
        Map<String, Object> oauth2attributes = null;
        String email = null;

        if(provider.equalsIgnoreCase("google")) { //equalsIgnoreCase 대소문자 구분x
            oauth2attributes = attributes;
        }else if(provider.equalsIgnoreCase("naver")){
            oauth2attributes = (Map<String, Object>) attributes.get("response");

        }

        email =(String) oauth2attributes.get("email");

        user = accountRepository.findUserByEmail(email);

        if(user == null){
            //회원가입
            user = User.builder()
                    .email(email)
                    .password(new BCryptPasswordEncoder().encode(UUID.randomUUID().toString()))
                    .name((String) attributes.get("name"))
                    .provider(provider)
                    .role_id(1)
                    .build();

            accountRepository.saveUser(user);

        }else if(user.getProvider() ==null ){
            // 연동

        }

        return new PrincipalDetails(user, attributes);
    }
}
