package com.koreait.first.board;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
//entity : db 테이블와 일치 domain : db에서 select용 dto : parameter용
public class BoardCmtDomain extends BoardCmtEntity {
    private String writeNm;
    private String profileImg;
}
