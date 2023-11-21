package com.example.greengram2.feed.model;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class FeedpicsInsPdto {
    private int ifeed;
    private List<String> pics;
}
