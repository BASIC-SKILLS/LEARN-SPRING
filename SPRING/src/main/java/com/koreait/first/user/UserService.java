package com.koreait.first.user;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserMapper mapper;

    public int join(UserEntity param) {
        String cryptPw = BCrypt.hashpw(param.getUpw(), BCrypt.gensalt());
        param.setUpw(cryptPw);
        return mapper.insUser(param);
    }

    public String login(UserEntity param) {
        UserEntity result = mapper.selUser(param);

        if (result==null) {
            return "/user/join?err=1";
        } else if (BCrypt.checkpw(param.getUpw(), result.getUpw())) {
            return "/board/list";
        } else {
            return "/user/login?err=1";
        }
    }
}
