package gocha.jjamppong.controller;


import gocha.jjamppong.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class AdminController {
    private final MemberService memberService;

    @GetMapping("/admin")
    public String adminIndex(Model model){
        return "admin/adminEx";
    }


}
