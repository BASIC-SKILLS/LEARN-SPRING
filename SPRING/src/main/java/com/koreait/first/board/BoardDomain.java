package com.koreait.first.board;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
//entity : db 테이블와 일치 domain : db에서 select용 dto : parameter용
public class BoardDomain extends BoardEntity {
    private String writeNm;
    private String profileImg;
}
