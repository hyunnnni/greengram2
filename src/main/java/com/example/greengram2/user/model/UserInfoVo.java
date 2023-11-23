package com.example.greengram2.user.model;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

@Data
public class UserInfoVo {
    private int feedCnt;//등록한  피드 수
    private int favCnt;//등록한 피드에 다린 좋아요 수
    private String nm;
    private String createdAt;
    private String pic;
}
