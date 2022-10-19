package com.stussy.stussyclone20220930kde.security;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AuthFailureHandler implements AuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        if(exception.getClass() == UsernameNotFoundException.class || exception.getClass() == BadCredentialsException.class) {
            //exception 요 객체가 usernameNotFoundException 과 같으면
            response.sendRedirect("/account/login?error=auth"); //지금까지 요청은 날려버리고 새로운 요청을 날린다. get요청

        }else if(exception.getClass() == CredentialsExpiredException.class){
            response.sendRedirect("/account/login?error=passwordExpired"); //지금까지 요청은 날려버리고 새로

        }else{
            response.sendRedirect("/account/login?error");
        }
    }
}
