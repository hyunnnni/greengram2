package com.example.greengram2.feed;

import com.example.greengram2.ResVo;
import com.example.greengram2.feed.model.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/feed")
@Tag(name ="피드API", description = "피드 관련 처리")
@Slf4j
public class FeedController {
    private final FeedService service;

    @Operation(summary = "피드 업로드", description = "피드 업로드 시 입력 정보")
    @Parameters(value = {
            @Parameter(name ="iuser", description = "업로드 유저"),
            @Parameter(name ="location", description = "장소"),
            @Parameter(name ="contents", description = "내용"),
            @Parameter(name ="pics", description = "사진")
    })
    @PostMapping
    public ResVo postFeedIns(@RequestBody FeedInsDto dto){
        return service.postFeedIns(dto);
    }

    @GetMapping
    public List<FeedSelVo> getFeedAll(int page, int loginedIuser,
                    @RequestParam(defaultValue = "0",required = false)int targetIuser){//좋아요 기능 때문에 보내는 파라미터
        //쿼리스트링url으로 받은 값을 파라미터에 매핑해준다
        //Pathvariable과 비슷한 기능 이름이 다르면 value를 사용해 바꾸고 defaultValue= 값이 없으면 기본으로 이 값을 넣어주겠ㄷ
        //required = false 무조건 값이 들어와야하는지 false는 아니여도 되지만 0으로 값이 들어갈거고 없다고 리턴될 것이다
        //true는 값을 필수로 넣어야 하니 defaultvalue가 없어도 된다.
        log.info("targetIuser : {}", targetIuser);
        final int ROW_COUNT = 30;
        FeedSelDto dto = FeedSelDto.builder()
                .loginedIuser(loginedIuser)
                .targetIuser(targetIuser)
                .rowCount(ROW_COUNT)
                .startIdx((page-1)*ROW_COUNT)
                .build();
        return service.getFeedAll(dto);
    }

    @Operation( summary = "좋아요 처리", description = "toggle로 처리함")
    @Parameters( value ={
            @Parameter (name = "ifeed", description = "좋아요 누를 피드의 ifeed"),
            @Parameter (name = "iuser", description = "로그인한 유저의 iuser")
    })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "좋아요 처리 : result(1), 좋아요 취소 : result(0)")
    })
    @GetMapping("/fav")//ResVo-result = insert: 1, delete: 0
    public ResVo toggleFav(FeedFavDto dto){
        return service.toggleFav(dto);
    }

    @Operation( summary = "댓글 작성", description = "댓글 작성 시 입력 정보")
    @Parameters( value ={
            @Parameter(name = "ifeed", description = "댓글 작성하고 싶은 피드의 ifeed"),
            @Parameter(name = "iuser", description = "댓글 작성한 유저의 iuser"),
            @Parameter(name = "comment", description = "댓글 내용")
    })
    @PostMapping("/comment")//댓글 정보 넣어주는 작업
    public ResVo postInsComm(@RequestBody FeedCommentInsDto dto){

        return service.postInsComm(dto);
    }

    @GetMapping("/comment")//댓글 더보기 눌렀을 때 전부 나오게 하는 작업
    public List<FeedCommentSelVo> getCommentAll(int ifeed){//쿼리 스트링으로 받는다
        return service.getCommentAll(ifeed);
    }

    @DeleteMapping("/comment")
    public ResVo delComment( @RequestParam("ifeed_comment") int ifeedComment,
                             @RequestParam("logined_iuser")int loginedIuser){
        log.info("ifeedComment : {}, loginedIuser:{}", ifeedComment, loginedIuser);

        FeedCommentDelDto dto = FeedCommentDelDto.builder()
                .iFeedComment(ifeedComment)
                .loginedIuser(loginedIuser)
                .build();

        return service.delComment(dto);
    }

    @DeleteMapping
    public ResVo delFeed(FeedDelDto dto){
        log.info("dto :{}", dto);
        return service.delFeed(dto);
    }
}
