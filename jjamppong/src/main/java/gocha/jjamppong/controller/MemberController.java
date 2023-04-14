package gocha.jjamppong.controller;

import gocha.jjamppong.dto.MemberDto;
import gocha.jjamppong.service.MemberService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

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

    @GetMapping("/members/login")
    public String login(Model model, HttpServletRequest request){

        model.addAttribute("LoginForm", new LoginForm() );

        HttpSession session = request.getSession(true);
        LoginForm getForm = (LoginForm) session.getAttribute("USER");


        // 이미 로그인 했다면 메인 페이지로 이동동
       if (getForm != null){
            return "redirect:/";
        }


        return "members/loginForm";
    }

//    @GetMapping("/members/logout")
//    public String logout(HttpServletRequest request) {
//        HttpSession session = request.getSession();
//        session.invalidate();
//
//        return "redirect:/";
//    }
    @GetMapping("/members/loginFail")
    public String logout(Model model) {
        model.addAttribute("message", "로그인에 실패했습니다.");
        model.addAttribute("searchUrl", "/members/login");

        return "message";
    }




//    //로그인 처리
//    @PostMapping("/members/loginpro")
//    public String loginProcess(Model model, LoginForm form, HttpServletRequest request){
//
////        System.out.println(form.getUserId());
////        System.out.println(form.getUserPassword());
//
//        if (memberService.authenticate(form).equals("SUCCESS")){
//
//            //세션 바인딩
//            HttpSession session = request.getSession(true);
//
//            session.setAttribute("USER", form);
//
//            LoginForm getForm = (LoginForm) session.getAttribute("USER");
//            model.addAttribute("message", getForm.getUserId());
//            model.addAttribute("searchUrl", "/");
//        }
//        else{
//            model.addAttribute("message", "등록되지 않은 사용자이거나 패스워드가 맞지않습니다.");
//            model.addAttribute("searchUrl", "/members/login");
//        }
//
//
//
//        return "message";
//    }

    // 회원가입 페이지 이동
    @GetMapping("/members/register")
    public String registerProcess(Model model){
        model.addAttribute("RegisterForm", new RegisterForm());

        return "members/registerForm";
    }

    // 회원가입 처리
    @PostMapping("/members/registerpro")
    public String register(Model model, RegisterForm form){
        model.addAttribute("RegisterForm", new RegisterForm());
        MemberDto memberDto = MemberDto.builder()
                .name(form.getUsername())
                .password(form.getUserPassword())
                .build();

        Long result = memberService.register(memberDto);

        if (result == -1l){
            model.addAttribute("searchUrl", "/members/register");
            model.addAttribute("message", "중복된 ID입니다");
        }
        else{
            model.addAttribute("searchUrl", "/");
            model.addAttribute("message", "회원가입하신 것을 축하드립니다");
        }

        return "message";
    }

}
