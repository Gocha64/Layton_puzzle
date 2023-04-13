package gocha.jjamppong.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class HomeController {

    @GetMapping("/")
    public String index(Model model){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username;
        // 로그인한 사용자 확인
        if (principal instanceof UserDetails) {
            username = ((UserDetails)principal).getUsername();
            model.addAttribute("isLogin", true);
            System.out.println(username);
        } else {
            username = principal.toString();
            model.addAttribute("isLogin", false);
            System.out.println(username);
        }

        model.addAttribute("username", username);

        return "index";
    }

    /*
    회원가입 중복 처리
    퍼즐 정답 처리
    맞춘 퍼즐들 조회
     */

}
