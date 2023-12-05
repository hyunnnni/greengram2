package com.example.greengram2.feed;

import com.example.greengram2.ResVo;
import com.example.greengram2.feed.model.*;
import com.example.greengram2.user.model.UserSignupPdto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
@Slf4j
public class FeedService {
    private final FeedMapper mapper;
    private final FeedPicsMapper picsMapper;
    private final FeedFavMapper favMapper;
    private final FeedCommentMapper comMapper;

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

        return new ResVo(pdto.getIfeed());
    }

    public List<FeedSelVo> getFeedAll(FeedSelDto dto){
        List<FeedSelVo> list = mapper.selFeedAll(dto);

        for(FeedSelVo vo : list){
            vo.setPics(picsMapper.selFeedPicsAll(vo.getIfeed()));

            List<FeedCommentSelVo> comments = comMapper.selCommentAll(FeedCommentSelDto.builder()
                    .ifeed(vo.getIfeed())
                    .startIdx(0)
                    .rowCount(4)
                    .build());
            if(comments.size() == 4){//해당 피드의 댓글을 가져오는 작업
                vo.setIsMoreComment(1);
                comments.remove(comments.size()-1);
            }
            vo.setComments(comments);
        }

        return list;
    }

    public ResVo toggleFav(FeedFavDto dto){
        int result = favMapper.delFeedFav(dto);
        if( result == 1){
            return new ResVo(0);
        }
        result = favMapper.insFeedFav(dto);
        return new ResVo(result);
    }

    public ResVo postInsComm(FeedCommentInsDto dto){
        FeedCommentDelInsPdto pdto = FeedCommentDelInsPdto.builder()
                .ifeed(dto.getIfeed())
                .iuser(dto.getIuser())
                .comment(dto.getComment())
                .build();
        int result = comMapper.insFeedComm(pdto);
        return new ResVo(pdto.getIFeedComment());
    }
    public List<FeedCommentSelVo> getCommentAll(int ifeed){// 댓글 더보기를 눌렀을 때 실행되는 메소드
        return comMapper.selCommentAll(FeedCommentSelDto.builder()
                        .ifeed(ifeed)
                        .startIdx(3)
                        .rowCount(9999)
                        .build());
    }

    public ResVo delComment(FeedCommentDelDto dto){
        log.info("dto {}", dto);
        int result = comMapper.delComment(dto);
        if(result == 1){
            return new ResVo(dto.getIFeedComment());
        }
        return new ResVo(result);
    }

    public ResVo delFeed(FeedDelDto dto){


        int iuser = mapper.seliuser(dto.getIfeed());
        int result = 0;

        if(iuser != dto.getIuser()){
            return new ResVo(result);
        }else if(iuser == dto.getIuser()){
            comMapper.delFeedAndComment(dto.getIfeed());
            picsMapper.delFeedPics(dto.getIfeed());
            favMapper.delFeedFavAll(dto.getIfeed());
            result = mapper.delFeed(dto);
        }
        return new ResVo(result);
    }
}
