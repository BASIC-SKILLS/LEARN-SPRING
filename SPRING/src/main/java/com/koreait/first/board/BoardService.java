package com.koreait.first.board;

import com.koreait.first.MyUtils;
import com.koreait.first.user.UserEntity;
import com.mysql.cj.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.List;

@Service
public class BoardService {

    @Autowired
    private BoardMapper mapper;

    @Autowired
    private BoardCmtMapper cmtMapper;

    @Autowired
    private HttpSession session;

    @Autowired
    private MyUtils myUtils;

    public List<BoardDomain> selBoardList() { return mapper.selBoardList(); }

    public BoardDomain selBoard(BoardDTO param) { return mapper.selBoard(param);}

    public int insBoardCmt(BoardCmtEntity param) {
        UserEntity loginUser = (UserEntity) session.getAttribute("loginUser");
        param.setIuser(loginUser.getIuser());
        return cmtMapper.insBoardCmt(param);
    }

    public List<BoardCmtDomain> selBoardCmtList(BoardCmtEntity param) {
        List<BoardCmtDomain> list = cmtMapper.selBoardCmtList(param);
        return list;
    }

    public int updBoardCmt(BoardCmtEntity param) {
        UserEntity loginUser = (UserEntity) session.getAttribute("loginUser");
        param.setIuser(loginUser.getIuser());
        return cmtMapper.updBoardCmt(param);
    }

    public int delBoardCmt(BoardCmtEntity param) {
        UserEntity loginUser = (UserEntity) session.getAttribute("loginUser");
        param.setIuser(loginUser.getIuser());
        return cmtMapper.delBoardCmt(param);
    }

    public int write(BoardEntity param) {
        param.setIuser(myUtils.getLoginUserPk()); //iboard, title, ctnt, iuser

        if(param.getIboard()==0) { //등록 - ins Mapper
            mapper.insBoard(param);
        } else {  //수정  - upd Mapper
            mapper.updBoard(param);
        }
        return param.getIboard();
    }
}
