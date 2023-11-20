package com.example.greengram2.user;


import com.example.greengram2.ResVo;
import com.example.greengram2.user.model.UserSigninDto;
import com.example.greengram2.user.model.UserSigninVo;
import com.example.greengram2.user.model.UserSignupDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
@Slf4j // log를 사용할 수 있는 애노테이션
//스프링프레임워크 로그 포 제이
public class UserController {
    private final UserService service;

    @PostMapping("/signup")
    public ResVo postSingup(@RequestBody UserSignupDto dto){
        log.info("dto: {}", dto);
        //{} = %와 같다
        //ResVo 객체에 insert한 레코드 pk값을 담아서 응답처리
        return service.postSignup(dto);
    }

    @PostMapping("/signin")
    public UserSigninVo postSignin(@RequestBody UserSigninDto dto){
        return service.postSignin(dto);
    }
}
