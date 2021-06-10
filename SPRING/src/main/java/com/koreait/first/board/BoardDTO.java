package com.koreait.first.board;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
//파라미터용
public class BoardDTO {
    private int iboard;
    private int startIdx;
    private int recordCnt;
    private int searchType;
    private String searchText;
}
