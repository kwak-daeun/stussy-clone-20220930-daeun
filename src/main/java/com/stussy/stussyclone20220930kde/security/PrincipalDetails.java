package com.stussy.stussyclone20220930kde.security;

import com.stussy.stussyclone20220930kde.domain.User;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

@Data
public class PrincipalDetails implements UserDetails {

    private User user;

    public PrincipalDetails(User user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>(); //arraylist에 upcasting한것
        authorities.add(() -> user.getRole().getName()); //객체생성
        return authorities;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() { //휴먼계정
        return true;
    }

    @Override
    public boolean isAccountNonLocked() { //블랙리스트, 잠궈버리는것
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() { //비밀번호 5회이상 틀리는 로직
        return true;
    }

    @Override
    public boolean isEnabled() { //비활성화
        return true;
    }
}
