package com.koreait.first.board;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/board")
public class BoardController {

    @Autowired
    BoardService service;

    @RequestMapping("/list")
    public String list(Model model) {
        List<BoardDomain> list = service.selBoardList();
        model.addAttribute("list", list);
        return "board/list";
    }

    @RequestMapping("/detail")
    public String detail(BoardDTO param, Model model) {
        System.out.println("iboard : " + param.getIboard());
        BoardDomain data = service.selBoard(param);
        model.addAttribute("data" , data);
        return "board/detail"; //jsp로 응답시켜주는 것이 목적이다
    }
    //이걸 적어준 순간 메소드의 목적은 jason형식으로 통신하는 것을 목표로 한다
    @ResponseBody//return해주는 걸 JSON 문자열을 return하는 것이 목적이 된다 //JSON을 쓰는 이유는 객체화 하기 너무 쉬워서 //html태그 주지 않고 JASON 문자열만 return해주는 것이 목적이예용 
    @RequestMapping(value = "/cmtIns", method = RequestMethod.POST)
    public Map<String, Integer> cmtInsSel(@RequestBody BoardCmtEntity param) { //requestbody로 해주면 JASON(STRING) > OBJECT, OBJECT > JASON(STRING) 컴퍼팅 해준다 BOARDCMTENTITY 객체 형식에 맞추어서
        System.out.println("param : " + param);

        int result = service.insBoardCmt(param);

        Map<String, Integer> data = new HashMap<>(); //html방식과 같다, 배열, 순서 없이 key와 value로만 이루어졌당 , html과 map은 for문과 foreach문을 돌릴 수 없당, html이 map방식이다. , for문 쓸려면 배열로 바꿔서 쓰면 되긴함
        data.put("result", result);
        return data; //이게 JASON형태로 바뀌는 이유는 JASCKSON라이브러리가 해줘서 이예용

    }

    @ResponseBody //응답을 JASON이니까 필수 //객체의 주소값만 리턴하면 객체를 JASON형태로  바꿔서 넘겨준다
    @RequestMapping("/cmtSel")
    public List<BoardCmtDomain> CmtSel(BoardCmtEntity param) {
        System.out.println(param);
        List<BoardCmtDomain> list = service.selBoardCmtList(param);
        return list;
    }
// DB > JAVA(객체) > JASON (STRING) > JS (JS객체)
    // JS(JS 객체) > JASON > 서버(JAVA객체) > DB
}
