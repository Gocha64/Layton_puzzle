package gocha.jjamppong.controller;

import gocha.jjamppong.dto.PuzzleRegisterForm;
import gocha.jjamppong.dto.PuzzleResponseDto;
import gocha.jjamppong.entity.Member;
import gocha.jjamppong.entity.Puzzle;
import gocha.jjamppong.entity.SolutionDetail;
import gocha.jjamppong.entity.SolvedPuzzle;
import gocha.jjamppong.dto.PuzzleAnswerSubmitForm;
import gocha.jjamppong.service.MemberService;
import gocha.jjamppong.service.PuzzleService;
import gocha.jjamppong.service.SolvedPuzzleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
@Slf4j
public class PuzzleController {

    private final PuzzleService puzzleService;
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

    //퍼즐 상세페이지
    @GetMapping("/puzzles/view/{puzzleId}")
    public String detail(@PathVariable("puzzleId") Long puzzleId, Model model){
        Puzzle puzzle = puzzleService.findOne(puzzleId);
        PuzzleResponseDto puzzleDto =  PuzzleResponseDto.toResponseDto(puzzle);
        puzzleDto.ChangeContentNewlineTrim();


        model.addAttribute("puzzle", puzzleDto);
        model.addAttribute("PuzzleAnswerSubmitForm", new PuzzleAnswerSubmitForm());

        return "puzzles/puzzleForm";
    }


    //페이징 처리해서 퍼즐 리스트를 보여줌
    @GetMapping("/puzzles/list")

    public String list(Model model, @PageableDefault(page = 0, size = 25, sort = "id", direction = Sort.Direction.ASC) Pageable pageable){
//        Page<Puzzle> puzzles = puzzleService.findPuzzlesWithPaging(pageable);
        Page<PuzzleResponseDto> puzzles =
                puzzleService.findPuzzlesWithPaging(pageable);


        int nowPage = puzzles.getPageable().getPageNumber() + 1;
        int startPage = Math.max(nowPage - 4, 1);
        int endPage = Math.min(nowPage + 5, puzzles.getTotalPages());
        int totalPage = puzzles.getTotalPages();

        model.addAttribute("puzzles", puzzles);
        model.addAttribute("nowPage", nowPage);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        model.addAttribute("totalPage", totalPage);

        return "puzzles/puzzleBoard";
    }

    // 제출 시 처리
    @PostMapping("/puzzles/submit/{puzzleId}")
    public String answerSubmit(Model model, @PathVariable("puzzleId") Long puzzleId,PuzzleAnswerSubmitForm form){
        Boolean check = puzzleService.checkAnswer(puzzleId, form);

        if (check){
            Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            Puzzle puzzle = puzzleService.findOne(puzzleId);
            Member member = memberService.findByName(((UserDetails)principal).getUsername());

            SolvedPuzzle solvedPuzzle = SolvedPuzzle.builder()
                    .member(member)
                    .puzzle(puzzle)
                    .score(puzzle.getDifficulty())
                    .build();

            // 이미 정답을 맟춘적이 있는지 확인
            if(!solvedPuzzleService.checkSolve(member, puzzle)){
                solvedPuzzleService.register(solvedPuzzle);
            }

            // 해설 페이지가 없다면?
            SolutionDetail solutionDetail = puzzle.getSolutionDetail();

            if (solutionDetail != null)
                model.addAttribute("solution", puzzle.getSolutionDetail());
            else
                model.addAttribute("solution", SolutionDetail.builder().content("").image_path("").build());

            return String.format("puzzles/solution");

        }
        else{
            model.addAttribute("message", "오답입니다... 다시 한 번 풀어보세요.");
            model.addAttribute("searchUrl", String.format("/puzzles/view/%d", puzzleId));

            return "message";
        }

    }

    @PostMapping("/puzzles/registerpro")
    public String register(Model model, PuzzleRegisterForm form){
        return "redirect:/";
    }




}
