package com.example.greengram2.user;

import com.example.greengram2.user.model.UserSigninDto;
import com.example.greengram2.user.model.UserSigninVo;
import com.example.greengram2.user.model.UserSignupDto;
import com.example.greengram2.user.model.UserSignupPdto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    int insSignup(UserSignupPdto dto);
    String selsignin(String uid);

    UserSigninVo selsigninUser(String uid);
}
