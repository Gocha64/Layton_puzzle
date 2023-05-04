package gocha.jjamppong.controller;


import gocha.jjamppong.dto.PuzzleRegisterForm;
import gocha.jjamppong.repository.PuzzleRepository;
import gocha.jjamppong.service.MemberService;
import gocha.jjamppong.service.PuzzleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

@Controller
@RequiredArgsConstructor
@Slf4j
public class AdminController {
    private final MemberService memberService;
    private final PuzzleService puzzleService;
    private final PuzzleRepository puzzleRepository;

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
    public String register(Model model){
        model.addAttribute("PuzzleRegisterForm", new PuzzleRegisterForm());
        return "admin/puzzleRegister";
    }


    @PostMapping("/admin/puzzle/registerpro")
    public String registerProcess(PuzzleRegisterForm form) throws Exception{

        log.info(form.getTitle());
        log.info(form.getContent());
        if (!form.getImage().isEmpty()){
            log.info(form.getImage().getOriginalFilename());
            String projectPath = System.getProperty("user.dir") + "\\src\\main\\resources\\static\\images";

            File saveFile = new File(projectPath, form.getImage().getOriginalFilename());
            form.getImage().transferTo(saveFile);
        }

        else
            log.info("fail to upload image");

        puzzleRepository.save(form.toEntity());

        return "redirect:/";
    }


}
