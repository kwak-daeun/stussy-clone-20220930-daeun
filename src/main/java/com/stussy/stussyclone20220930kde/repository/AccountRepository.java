package com.stussy.stussyclone20220930kde.repository;

import com.stussy.stussyclone20220930kde.domain.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper //ioc등록, xml 연결 가능

public interface AccountRepository {

    public User findUserByEmail(String email)throws Exception;
    public int saveUser(User user)throws Exception;

}
