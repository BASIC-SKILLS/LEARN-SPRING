package com.koreait.facebook.common;

import org.springframework.stereotype.Component;

@Component
public class MySecurityUtils {
    public int getRandomNumber(int eNum) {
        return getRandomNumber(0, eNum);
    }

    public int getRandomNumber(int sNum, int eNum) {
        return (int) (Math.random() * (eNum - sNum + 1)) + sNum;
    }

    // len 길이만큼의 랜덤한 숫자(0~9)를 문자열로 리턴
    public String getRandomCode(int len) {
        StringBuilder code = new StringBuilder();
        for(int i=0; i<len; i++) {
            code.append(getRandomNumber(9));
        }
        return code.toString();
    }
}
