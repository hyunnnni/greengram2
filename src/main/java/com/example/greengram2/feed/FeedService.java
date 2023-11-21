package com.example.greengram2.feed;

import com.example.greengram2.ResVo;
import com.example.greengram2.feed.model.*;
import com.example.greengram2.user.model.UserSignupPdto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class FeedService {
    private final FeedMapper mapper;
    private final FeedPicsMapper picsMapper;

    public ResVo postFeedIns(FeedInsDto dto){
        FeedInsPdto pdto = FeedInsPdto.builder()
                .iuser(dto.getIuser())
                .contents(dto.getContents())
                .location(dto.getLocation())
                .build();
        int iresult = mapper.insFeed(pdto);
        FeedpicsInsPdto picsPdto = FeedpicsInsPdto.builder()
                .ifeed(pdto.getIfeed())
                .pics(dto.getPics())
                .build();
        int presult = mapper.insFeedPic(picsPdto);
        if(iresult == 0 || presult == 0){
            return new ResVo(iresult);
        }

        return new ResVo(pdto.getIfeed());
    }

    public List<FeedSelVo> getFeedAll(FeedSelDto dto){
        List<FeedSelVo> list = mapper.selFeedAll(dto);

        for(FeedSelVo vo : list){
            vo.setPics(picsMapper.selFeedPicsAll(vo.getIfeed()));
        }

        return list;
    }
}
