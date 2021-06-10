package com.koreait.first.board;

import com.koreait.first.user.UserEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
//뭐든지간에 CRUD순서로 할 것~!!
@Mapper
public interface BoardMapper {
    int insBoard(BoardEntity param);
    List<BoardDomain> selBoardList();
    BoardDomain selBoard(BoardDTO param);
    int updBoard(BoardEntity param);
}
