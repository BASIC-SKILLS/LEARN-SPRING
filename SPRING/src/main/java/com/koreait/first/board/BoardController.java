package com.koreait.first.board;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

//crud 순서로 적어준당

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
    @RequestMapping(value = "/cmt", method = RequestMethod.POST)
    public Map<String, Integer> cmtInsSel(@RequestBody BoardCmtEntity param) { //requestbody로 해주면 JASON(STRING) > OBJECT, OBJECT > JASON(STRING) 컴퍼팅 해준다 BOARDCMTENTITY 객체 형식에 맞추어서
        System.out.println("param : " + param);

        int result = service.insBoardCmt(param);

        Map<String, Integer> data = new HashMap<>(); //html방식과 같다, 배열, 순서 없이 key와 value로만 이루어졌당 , html과 map은 for문과 foreach문을 돌릴 수 없당, html이 map방식이다. , for문 쓸려면 배열로 바꿔서 쓰면 되긴함
        data.put("result", result);
        return data; //이게 JASON형태로 바뀌는 이유는 JASCKSON라이브러리가 해줘서 이예용

    }

    @ResponseBody //응답을 JASON이니까 필수 //객체의 주소값만 리턴하면 객체를 JASON형태로  바꿔서 넘겨준다
    @RequestMapping("/cmt/{iboard}")  //{}에 들어가는 값을 @PathVariable("")에 넣는당 왜냐면 변수명이 {}이랑 다를 경우 스프링이 알아 봐야 하니까, 만약에 변수랑 {}랑 같으면 안적어도 됑 ㅎㅎ
    public List<BoardCmtDomain> CmtSel(BoardCmtEntity param, @PathVariable("iboard") int iboard) {
        //BoardCmtEntity param = new BoardCmtEntity();  //여기서는 IOC랑 상관이 없어서 new를 썻다 vo는 IOC랑 상관이 없당 // IOC대로 개발하는 것과 new를 최소화한다는 거는 아니당 그냥 스프링에게 객체 생성을 맡긴당
        param.setIboard(iboard);
        List<BoardCmtDomain> list = service.selBoardCmtList(param);
        return list;
    }
// DB > JAVA(객체) > JASON (STRING) > JS (JS객체)
    // JS(JS 객체) > JASON > 서버(JAVA객체) > DB

    @ResponseBody
    @RequestMapping(value = "/cmt", method = RequestMethod.PUT)
    public Map<String, Integer> cmtUpd(@RequestBody BoardCmtEntity param) {
        int result = service.updBoardCmt(param);
        Map<String, Integer> data = new HashMap<>();
        data.put("result", result);
        return data;
    }


    @ResponseBody
    @RequestMapping(value = "/cmt/{icmt}", method = RequestMethod.DELETE)
    public Map<String, Integer> CmtDel(BoardCmtEntity param, @PathVariable int icmt) {
        param.setIcmt(icmt);
        int result = service.delBoardCmt(param);
        Map<String, Integer> data = new HashMap<>();
        data.put("result", result);
        return data;
    }
}

//bean등록이 되면 싱글톤이 된당 <- lombok은 bean등록 아니다 . 그래서 어노테이션이 꼭 싱글톤으로 객체 만들어주는 것은 아니다
//싱글톤으로 돌아간다는 것만 알면 된다 그렇게 신경써야 할 것은 아니다
