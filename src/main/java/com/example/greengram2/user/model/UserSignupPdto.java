package com.example.greengram2.user.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
public class UserSignupPdto {
    private String uid;
    private String upw;
    private String nm;

    private String pic;
    private int iuser;



}
