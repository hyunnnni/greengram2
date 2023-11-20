package com.example.greengram2.user;


import com.example.greengram2.ResVo;
import com.example.greengram2.user.model.UserSigninDto;
import com.example.greengram2.user.model.UserSigninVo;
import com.example.greengram2.user.model.UserSignupDto;
import com.example.greengram2.user.model.UserSignupPdto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {
    private final UserMapper mapper;

    public ResVo postSignup(UserSignupDto dto){
        String hashedPw = BCrypt.hashpw(dto.getUpw(), BCrypt.gensalt());
        //gensalt salt를 만든다라는 메소드 암호를 강력하게 해줌
        //암호화하기 위한 작업

        log.info("hashedPw = {}",hashedPw);

        UserSignupPdto pdto = UserSignupPdto.builder()
                .uid(dto.getUid())
                .upw(hashedPw)
                .nm(dto.getNm())
                .pic(dto.getPic())
                .build();
        int affectedrows = mapper.insSignup(pdto);
        if(affectedrows == 0){//혹시나 INSERT 실패 시 리턴은 0으로 한다
            return new ResVo(0);
        }
        return new ResVo(pdto.getIuser());
    }

    public UserSigninVo postSignin(UserSigninDto dto){
        String savedPw = mapper.selsignin(dto.getUid());
        UserSigninVo svo = new UserSigninVo();
        svo.setResult(3);
        if (savedPw == null){
            svo.setResult(2);
            return svo;
        }

        boolean comparedPw = BCrypt.checkpw(dto.getUpw(), savedPw);
        if(comparedPw) {
            svo = mapper.selsigninUser(dto.getUid());
            svo.setResult(1);
        }
        return svo;
    }
}
