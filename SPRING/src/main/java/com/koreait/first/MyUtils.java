package com.koreait.first;

import com.koreait.first.user.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;

@Component //bean등록
public class MyUtils {
    @Autowired
    private HttpSession session;

    public int getLoginUserPk() {
        return getLoginUser().getIuser();
    }

    public UserEntity getLoginUser() { return (UserEntity) session.getAttribute("loginUser");}

}
