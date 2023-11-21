package com.example.greengram2.feed.model;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.List;

@Data
public class FeedInsDto {
    private int iuser;
    private String contents;
    private String location;
    private List<String> pics;
}
