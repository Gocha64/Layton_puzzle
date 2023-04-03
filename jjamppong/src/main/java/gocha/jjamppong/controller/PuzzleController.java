package gocha.jjamppong.controller;

import gocha.jjamppong.Entity.Puzzle;
import gocha.jjamppong.service.PuzzleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class PuzzleController {

    private final PuzzleService puzzleService;

    @GetMapping("/puzzle/{puzzleId}")
    public String detail(@PathVariable("puzzleId") String puzzleId, Model model){

        return "puzzles/puzzleForm";
    }

    // !페이징 처리할 것
    @GetMapping("/puzzles")
    public String list(Model model){
        List<Puzzle> puzzles = puzzleService.findPuzzles();

        model.addAttribute("puzzles", puzzles);

        return "puzzles/puzzleForm";
    }


}
