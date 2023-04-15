package gocha.jjamppong.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.Collection;

@Controller
@RequiredArgsConstructor
public class HomeController {

    @ModelAttribute
    public void addRole(Model model){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails){
            model.addAttribute("role", ((UserDetails)principal).getAuthorities().toString());
            model.addAttribute("userName", ((UserDetails)principal).getUsername());
        }
        else{
            model.addAttribute("role", null);
            model.addAttribute("userName", null);
        }
    }

    @GetMapping("/")
    public String index(Model model){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username;

        // 로그인한 사용자 확인
        if (principal instanceof UserDetails) {
            username = ((UserDetails)principal).getUsername();
        }
        else {
            username = principal.toString();
        }

//        System.out.println(username);
        model.addAttribute("username", username);

        return "index";
    }

    /*
    퍼즐 정답 처리
    맞춘 퍼즐들 조회
    문자열 트리밍
    관리자 페이지
    관리자 퍼즐등록/삭제
     */

}
