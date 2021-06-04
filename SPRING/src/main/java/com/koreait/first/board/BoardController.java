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

    @ResponseBody//return해주는 걸 JSON 문자열을 return하는 것이 목적이 된다 //JSON을 쓰는 이유는 객체화 하기 너무 쉬워서 //html태그 주지 않고 JASON 문자열만 return해주는 것이 목적이예용 
    @RequestMapping(value = "/cmtInsSel", method = RequestMethod.POST)
    public Map<String, Integer> cmtInsSel(@RequestBody BoardCmtEntity param) {
        System.out.println("param : " + param);
        Map<String, Integer> result = new HashMap<>(); //html방식과 같다, 배열, 순서 없이 key와 value로만 이루어졌당 , html과 map은 for문과 foreach문을 돌릴 수 없당, html이 map방식이다. , for문 쓸려면 배열로 바꿔서 쓰면 되긴함
        result.put("result", 1);
        return result; //이게 JASON형태로 바뀌는 이유는 JASCKSON라이브러리가 해줘서 이예용
    }
}
