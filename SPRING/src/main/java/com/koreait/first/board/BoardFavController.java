package com.koreait.first.board;

import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

// 이 어노테이션을 준 순간 이 컨트롤러의 목적은 JASON을 retrun하는 것으로 바뀐다
@RestController
@RequestMapping("/board")
public class BoardFavController {
    @PostMapping("/fav")
    public Map<String, Integer> insFav(@RequestBody BoardFavEntity param) {
        Map<String, Integer> result = new HashMap<>();
        return result;
    }

    @GetMapping("/fav")
    public Map<String, Integer> selFav(@RequestBody BoardFavEntity param) {
        Map<String, Integer> result = new HashMap<>();
        return result;
    }

    @DeleteMapping("/fav")
    public Map<String, Integer> delFav(@RequestBody BoardFavEntity param) {
        Map<String, Integer> result = new HashMap<>();
        return result;
    }
}
