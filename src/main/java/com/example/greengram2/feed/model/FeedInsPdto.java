package com.example.greengram2.feed.model;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class FeedInsPdto {
    private int ifeed;
    private int iuser;
    private String contents;
    private String location;
}
