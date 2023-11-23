package com.example.greengram2.feed;

import com.example.greengram2.feed.model.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface FeedMapper {
    int insFeed(FeedInsPdto pdto);

    int insFeedPic(FeedpicsInsPdto picsPdto);

    List<FeedSelVo> selFeedAll(FeedSelDto dto);

    int delFeed(FeedDelDto dto);

    int seliuser(int ifeed);
}
