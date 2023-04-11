package gocha.jjamppong.controller;

import gocha.jjamppong.service.MemberService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/members/login")
    public String login(Model model, HttpServletRequest request){

        model.addAttribute("LoginForm", new LoginForm() );

        HttpSession session = request.getSession(true);
        LoginForm getForm = (LoginForm) session.getAttribute("USER");

        if (getForm != null){
            model.addAttribute("message", "이미 로그인 했어");
            model.addAttribute("searchUrl", "/");
            return "message";
        }


        return "members/loginForm";
    }

    @PostMapping("/members/login")
    public String loginProcess(Model model, LoginForm form, HttpServletRequest request){

//        System.out.println(form.getUserId());
//        System.out.println(form.getUserPassword());

        if (memberService.authenticate(form).equals("SUCCESS")){

            //세션 바인딩
            HttpSession session = request.getSession(true);

            session.setAttribute("USER", form);

            LoginForm getForm = (LoginForm) session.getAttribute("USER");
            model.addAttribute("message", getForm.getUserId());
            model.addAttribute("searchUrl", "/");
        }
        else{
            model.addAttribute("message", "로그인 실패...");
            model.addAttribute("searchUrl", "/members/login");
        }



        return "message";
    }
}
