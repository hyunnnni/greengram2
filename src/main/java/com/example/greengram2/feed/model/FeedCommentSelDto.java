package com.example.greengram2.feed.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FeedCommentSelDto {
    private int ifeed;
    private int startIdx;
    private int rowCount;
}
