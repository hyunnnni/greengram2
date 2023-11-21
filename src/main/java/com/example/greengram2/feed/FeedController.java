package com.example.greengram2.feed;

import com.example.greengram2.ResVo;
import com.example.greengram2.feed.model.FeedInsDto;
import com.example.greengram2.feed.model.FeedSelDto;
import com.example.greengram2.feed.model.FeedSelVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
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
        //쿼리스트링으로 받을 때 파라미터 앞에 자동으로 붙는다
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
}
