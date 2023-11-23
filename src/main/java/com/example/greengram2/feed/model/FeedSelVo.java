package com.example.greengram2.feed.model;

import lombok.Data;

import java.util.List;

@Data
public class FeedSelVo {
    private int ifeed;
    private String contents;
    private String location;
    private int writerIuser;
    private String writerNm;
    private String writerPic;
    private String createdAt;
    private int isFav; //해당피드에 좋아요가 있다면 1 없다면 0
    private List<String> pics;
    private List<FeedCommentSelVo> comments;
    private int isMoreComment;//0없음 1 더있음
}
