package com.example.greengram2.feed;

import com.example.greengram2.feed.model.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface FeedCommentMapper {
    int insFeedComm(FeedCommentDelInsPdto pdto);

    List<FeedCommentSelVo> selCommentAll(FeedCommentSelDto dto);

    int delComment (FeedCommentDelDto dto);

    int delFeedAndComment(int ifeed);
}
