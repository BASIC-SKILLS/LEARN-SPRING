package com.koreait.first.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService service;

    @RequestMapping("/login")
    //err를 getparameter해주는데 int형이면 자동으로 형변환도 해준다
    //하지만 key가 err인 parameter가 꼭 있어야 한다. 없다면 404에러가 터진다.
    //또한 key가 err인데도 지정한 type이 아니면 404에러 나온다.
    //하지만 그냥 로그인하러 접속했을 때는 ... 없는 게 정상인데?
    //이때 강제성을 없도록 할 수 있다 -> false속성, String은 여기까지만 하면 ok -> @RequestParam(value="err", required=false, defaultValue="0") String err
    //그런데 int형이면 defaultValue만 정해주면 된다. 그러면 계속 404에러 -> @RequestParam(value="err", defaultValue="0") int err
    //spring에서는 파라미터 순서가 상관이 없다. spring이 알아서 type에 맞춰서 들어간다. java에서는 순서대로 넣어줬어야 했는데.
    //model객체가 알아서 request에 setAttribute 해주는 기능 밖에 없대요.
    public void login(@RequestParam(value="err", defaultValue="0") int err, Model model) {
        if (err==1) { model.addAttribute("errMsg","비밀번호를 확인해주세요."); }
    }

    @RequestMapping(value="/login", method = RequestMethod.POST)
    public String login(UserEntity param) {
        return "redirect:" + service.login(param);
    }

    @RequestMapping("/join")
    public void join(Model model) {
        model.addAttribute("errMsg","아이디가 없어요. 회원가입해주세요");
    }

    @RequestMapping(value="/join", method = RequestMethod.POST)
    public String join(UserEntity param) {
        System.out.println("param = " + param);
        service.join(param);
        return "redirect: /user/login";
    }

    @GetMapping("/profile")
    public void profile() { }   //화면 보여줄 때는 무조건 get방식이잖아. 이때 주소랑 파일명이 같으면 이렇게 void해도 된당

    //@RequestMapping(value = "/profile", method = RequestMethod.POST)
    //@GetMapping, @PostMapping, @DeleteMapping, @PutMapping
    @PostMapping("/profile") //여러 개 이면 MultipartFile[]이다
    public String profile(@RequestParam("profileImg") MultipartFile img) {
        return "redirect:"+service.uploadProfile(img);
    }

    @GetMapping("/logout")
    public String logout(HttpSession session, HttpServletRequest request) {
        session.invalidate();
        //return "redirect:/board/list";
        String refer = request.getHeader("Referer"); //Referer : request의 header에 저장된 전페이지
        return "redirect:"+refer;
    }
}
