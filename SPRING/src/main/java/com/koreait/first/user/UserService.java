package com.koreait.first.user;

import com.mysql.cj.Session;
import org.apache.commons.io.FilenameUtils;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Service
public class UserService {

    @Autowired
    private UserMapper mapper;

    @Autowired
    private HttpSession session;

    public int join(UserEntity param) {
        String cryptPw = BCrypt.hashpw(param.getUpw(), BCrypt.gensalt());
        param.setUpw(cryptPw);
        return mapper.insUser(param);
    }

    public String login(UserEntity param) {
        UserEntity result = mapper.selUser(param);

        if (result==null) {
            return "/user/join?err=1";
        } else if (BCrypt.checkpw(param.getUpw(), result.getUpw())) {
            result.setUpw(null);
            session.setAttribute("loginUser", result);
            return "/board/list";
        } else {
            return "/user/login?err=1";
        }
    }

    //img업로드를 d:/imgs로 할 것이다

    public String uploadProfile(MultipartFile img) {
        UserEntity loginUser = (UserEntity) session.getAttribute("loginUser");
        final String PATH = "D:/imgs/"+loginUser.getIuser(); //경로

        File folder = new File(PATH);
        folder.mkdirs();

        //upload한 파일 정보가 들어있는 img의 원래 올렸던 파일명에서 확장자를 가져오는 메소드
        String ext = FilenameUtils.getExtension(img.getOriginalFilename());

        //random파일 명 설정
        String fileNm = UUID.randomUUID().toString()+ "." + ext;

        //실제로 위치했으면 하는 위치와 파일명
        File target = new File(PATH + "/" + fileNm);

        try {
            img.transferTo(target);

            //이전 이미지 삭제
            File delFile = new File(PATH+"/"+loginUser.getProfileImg());
            if(delFile.exists()) {
                delFile.delete();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        UserEntity param = new UserEntity();
        param.setIuser(loginUser.getIuser());
        param.setProfileImg(fileNm);
        mapper.updUser(param);

        loginUser.setProfileImg(fileNm);

        return "/user/profile";
    }
}
