package com.example.greengram2.feed.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FeedCommentDelDto {
    private int iFeedComment;
    private int loginedIuser;
}
