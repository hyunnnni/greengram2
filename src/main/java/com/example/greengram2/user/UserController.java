package com.example.greengram2.user;


import com.example.greengram2.ResVo;
import com.example.greengram2.user.model.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
@Slf4j // log를 사용할 수 있는 애노테이션
//스프링프레임워크 로그 포 제이
@Tag(name="유저 API", description = "인증 관련")//swagger에서 controller url있는 부분을 변경
//이름을 붙여놓으면 나중에 알기 쉽다
public class UserController {
    private final UserService service;

    @Operation(summary = "회원가입", description = "")// 각 메소드마다 이름이 붙여지며
    //swagger에서 어떤 controller인지 더 알기 쉬워졌다
    //@Parameter(name ="dto", description = "uid : 아이디, upw : 비밀번호") 한번에 받을 수 있다
    @Parameters(value = {
            @Parameter(name ="uid", description = "아이디"),
            @Parameter(name ="upw", description = "비밀번호"),
            @Parameter(name ="nm", description = "이름"),
            @Parameter(name ="pic", description = "프로필 사진")
    })//각각 하나씩 받을 수 있다
    @PostMapping("/signup")
    public ResVo postSingup( @RequestBody UserSignupDto dto){
        log.info("dto: {}", dto);
        //{} = %와 같다
        //ResVo 객체에 insert한 레코드 pk값을 담아서 응답처리
        return service.postSignup(dto);
    }
    @Operation(summary = "인증", description = "아이디/ 비밀번호를 활용한 인증 처리")
    @Parameters(value = {
            @Parameter(name = "uid", description = "아이디"),
            @Parameter(name = "upw", description = "비밀번호")
    })
    @PostMapping("/signin")
    public UserSigninVo postSignin(@RequestBody UserSigninDto dto){
        return service.postSignin(dto);
    }
    //난 너무못해 바본가바 ㅜㅜ
    @GetMapping
    public UserInfoVo getprofile(@RequestParam(value = "target_iuser", required = true) int targetIuser){
        log.info("targetIuser : {} ", targetIuser);
        return service.getprofile(targetIuser);
    }
    //멍청해서 너무 힘들어ㅜㅜ
    @PatchMapping("/pic")
    public ResVo patchUserPic(@RequestBody UserPatchPicDto dto){
        return service.patchUserPic(dto);
    }
}
