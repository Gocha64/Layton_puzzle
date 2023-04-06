package gocha.jjamppong.controller;

import gocha.jjamppong.entity.Puzzle;
import gocha.jjamppong.service.PuzzleService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class PuzzleController {

    private final PuzzleService puzzleService;

    //퍼즐 상세페이지
    @GetMapping("/puzzle/{puzzleId}")
    public String detail(@PathVariable("puzzleId") String puzzleId, Model model){

        return "puzzles/puzzleForm";
    }


    //페이징 처리해서 퍼즐 리스트를 보여줌
    @GetMapping("/puzzles")
    public String list(Model model, @PageableDefault(page = 0, size = 25, sort = "id", direction = Sort.Direction.ASC) Pageable pageable){
        Page<Puzzle> puzzles = puzzleService.findPuzzlesWithPaging(pageable);

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


}
