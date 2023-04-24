package gocha.jjamppong.service;

import gocha.jjamppong.entity.Hint;
import gocha.jjamppong.entity.Puzzle;
import gocha.jjamppong.repository.HintRepository;
import gocha.jjamppong.repository.PuzzleRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class HintServiceTest {

    @Autowired
    PuzzleService puzzleService;
    @Autowired
    PuzzleRepository puzzleRepository;

    @Autowired
    HintRepository hintRepository;
    @Autowired
    HintService hintService;


    @Test
    public void 힌트등록() throws Exception {
        //given
        Long difficulty = 40L;
        String title = "title";
        String image_path = "/";
        String content = "content";
        String solution = "solution";
        String puzzle_code = "1-1";
        Puzzle puzzle = new Puzzle(title, difficulty, image_path, content, solution, puzzle_code);

        Hint hint1 = Hint.builder()
                .content("content1")
                .image_path("/")
                .level(1)
                .build();
        Hint hint2 = Hint.builder()
                .content("content2")
                .image_path("/")
                .level(2)
                .build();
        Hint hint3 = Hint.builder()
                .content("content3")
                .image_path("/")
                .level(3)
                .build();
        //when

        puzzleService.register(puzzle);
        puzzle.addHint(hint1);
        puzzle.addHint(hint2);
        puzzle.addHint(hint3);

        hintService.register(hint1);
        hintService.register(hint2);
        hintService.register(hint3);

        Puzzle findByIdPuzzle = puzzleRepository.findById(puzzle.getId()).orElse(null);


//        System.out.println(findByIdPuzzle.getHints());


        //then
        assertEquals(hint1, hintRepository.findOne(hint1.getId()));
        assertEquals(hint2, hintRepository.findOne(hint2.getId()));
        assertEquals(hint3, hintRepository.findOne(hint3.getId()));

    }

}