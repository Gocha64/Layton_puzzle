package gocha.jjamppong.service;

import gocha.jjamppong.Entity.Puzzle;
import gocha.jjamppong.repository.PuzzleRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class PuzzleServiceTest {
    @Autowired PuzzleService puzzleService;
    @Autowired PuzzleRepository puzzleRepository;

    @Test
    public void 퍼즐등록() throws Exception{
        //given
        Long difficulty = 40L;
        String image_path = "/";
        String content = "content";
        String solution = "solution";
        String puzzle_code = "1-1";
        Puzzle puzzle = Puzzle.builder()
                .difficulty(difficulty)
                .content(content)
                .solution(solution)
                .image_path(image_path)
                .puzzle_code(puzzle_code)
                .build();

        //when
        Long savedId = puzzleService.register(puzzle);

        //then
        assertEquals(puzzle, puzzleRepository.findOne(savedId));
    }


}