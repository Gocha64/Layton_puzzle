package gocha.jjamppong.controller;

import gocha.jjamppong.dto.MemberDto;
import gocha.jjamppong.dto.PuzzleResponseDto;
import gocha.jjamppong.entity.Member;
import gocha.jjamppong.entity.Puzzle;
import gocha.jjamppong.entity.SolvedPuzzle;
import gocha.jjamppong.dto.LoginForm;
import gocha.jjamppong.enums.UserAuthority;
import gocha.jjamppong.service.MemberService;
import gocha.jjamppong.service.SolvedPuzzleService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;
    private final SolvedPuzzleService solvedPuzzleService;

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

    @GetMapping("/members/loginFail")

    public String logout(Model model) {
        model.addAttribute("message", "로그인에 실패했습니다.");
        model.addAttribute("searchUrl", "/members/login");

        return "message";
    }

    @GetMapping("/members/mypage")
    public String myPage(Model model, @PageableDefault(page = 0, size = 25, sort = "id", direction = Sort.Direction.ASC) Pageable pageable) {

        Member member;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails){
            member = memberService.findByName(((UserDetails)principal).getUsername());
            Page<PuzzleResponseDto> solvedPuzzles =
                    solvedPuzzleService.findSolvedPuzzlesWithPaging(member, pageable).map(PuzzleResponseDto::toResponseDto);

            int nowPage = solvedPuzzles.getPageable().getPageNumber() + 1;
            int startPage = Math.max(nowPage - 4, 1);
            int endPage = Math.min(nowPage + 5, solvedPuzzles.getTotalPages());
            int totalPage = solvedPuzzles.getTotalPages();

            model.addAttribute("solvedPuzzles", solvedPuzzles);
            model.addAttribute("nowPage", nowPage);
            model.addAttribute("startPage", startPage);
            model.addAttribute("endPage", endPage);
            model.addAttribute("totalPage", totalPage);
        }
        else{
            model.addAttribute("solvedPuzzles", new SolvedPuzzle());
        }

        return "members/solvedPuzzleList";
    }

    // 회원가입 페이지 이동
    @GetMapping("/members/register")
    public String registerProcess(Model model){
        model.addAttribute("RegisterForm", new LoginForm());

        return "members/registerForm";
    }

    // 회원가입 처리
    @PostMapping("/members/registerpro")
    public String register(Model model, LoginForm form){
        model.addAttribute("RegisterForm", new LoginForm());
        MemberDto memberDto = MemberDto.builder()
                .name(form.getUsername())
                .password(form.getUserPassword())
                .authority(UserAuthority.ROLE_USER)
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
