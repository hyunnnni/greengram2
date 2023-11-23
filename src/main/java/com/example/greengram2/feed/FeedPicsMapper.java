package com.example.greengram2.feed;

import com.example.greengram2.feed.model.FeedSelDto;
import com.example.greengram2.feed.model.FeedSelVo;
import com.example.greengram2.feed.model.FeedpicsInsPdto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface FeedPicsMapper {
    List<String> selFeedPicsAll(int ifeed);

    int delFeedPics(int ifeed);
}
