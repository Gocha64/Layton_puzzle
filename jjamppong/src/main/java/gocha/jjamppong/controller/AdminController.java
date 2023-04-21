package gocha.jjamppong.controller;


import gocha.jjamppong.dto.PuzzleRegisterForm;
import gocha.jjamppong.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
@RequiredArgsConstructor
public class AdminController {
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

    @GetMapping("/admin")
    public String adminIndex(Model model){
        return "admin/adminEx";
    }

    @GetMapping("/admin/puzzle/register")
    public String registerPuzzle(Model model){
        model.addAttribute("PuzzleRegisterForm", new PuzzleRegisterForm());
        return "admin/puzzleRegister";
    }


}
