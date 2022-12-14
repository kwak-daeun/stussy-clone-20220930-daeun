package com.stussy.stussyclone20220930kde.config;

import com.stussy.stussyclone20220930kde.security.AuthFailureHandler;
import com.stussy.stussyclone20220930kde.service.PrincipalOauth2Service;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@EnableWebSecurity
@Configuration
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final PrincipalOauth2Service principalOauth2Service;
    //service annotation 달아서 ioc 해놔서 객체주입할 수 있음

    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.httpBasic().disable(); //로그인 페이지 안쓰겠다
        http.authorizeRequests() //모든 요청 , 권한이 들온다면
                .antMatchers("/account/mypage", "/index", "/checkout")
                .authenticated()
//                .antMatchers("/admin/**")
//                .hasRole("ADMIN")
                .antMatchers("/admin/**","/api/admin/**")
                .permitAll()
                .anyRequest()
                .permitAll()
                .and()
                .formLogin()
                .usernameParameter("email")
                .loginPage("/account/login")            //login page Get요청
                .loginProcessingUrl("/account/login")   //login service Post요청
                .failureHandler(new AuthFailureHandler())
                .defaultSuccessUrl("/index")
                .and()
                .oauth2Login()
                .userInfoEndpoint()
                .userService(principalOauth2Service)
                .and()
                .defaultSuccessUrl("/index");
    }
}
