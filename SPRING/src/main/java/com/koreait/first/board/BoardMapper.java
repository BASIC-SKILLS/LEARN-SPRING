package com.koreait.first.board;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BoardMapper {
    List<BoardDomain> selBoardList();
    BoardDomain selBoard(BoardDTO param);
}
