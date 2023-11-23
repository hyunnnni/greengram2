package com.example.greengram2.feed;

import com.example.greengram2.feed.model.FeedFavDto;
import com.example.greengram2.user.model.UserInfoVo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface FeedFavMapper {
    int delFeedFav(FeedFavDto dto);

    int insFeedFav(FeedFavDto dto);

    int delFeedFavAll(int ifeed);
}
