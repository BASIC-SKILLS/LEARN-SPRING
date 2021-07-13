package com.koreait.facebook.user;

import com.koreait.facebook.common.EmailServiceImpl;
import com.koreait.facebook.common.MySecurityUtils;
import com.koreait.facebook.user.model.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserMapper mapper;

    @Autowired
    private EmailServiceImpl email;

    @Autowired
    private MySecurityUtils secUtils;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public int join(UserEntity param) {
        // 0~9까지의 랜덤숫자의 조합이 5글자
        String authCd = secUtils.getRandomCode(5);

        //String hashedPw = BCrypt.hashpw(param.getPw(), BCrypt.gensalt());
        String hashedPw = passwordEncoder.encode(param.getPw());
        param.setPw(hashedPw);
        param.setAuthCd(authCd);
        int result = mapper.join(param);

        if (result == 1) {
            String subject="[얼굴책] 인증메일이예요.";
            String text = String.format("<a href=\"http://localhost:8090/user/auth?email=%s&authCd=%s\">인증하기</a>"
                            , param.getEmail(), authCd );
            email.sendMimeMessage(param.getEmail(), subject, text);
        }
        return result;
    }

    public int auth(UserEntity param) {
        return mapper.auth(param);
    }

}
