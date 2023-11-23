package com.example.greengram2.user;

import com.example.greengram2.user.model.*;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    int insSignup(UserSignupPdto dto);
    String selsignin(String uid);

    UserSigninVo selsigninUser(String uid);

    UserInfoVo selFeedFav(int targetIuser);

    int upUserPic(UserPatchPicDto dto);
}
