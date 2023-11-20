package com.example.greengram2.user.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class UserSignupDto {
    private String uid;
    private String upw;
    private String nm;
    private String pic;

}
