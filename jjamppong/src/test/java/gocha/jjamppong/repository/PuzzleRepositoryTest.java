package gocha.jjamppong.repository;

import gocha.jjamppong.Entity.Member;
import gocha.jjamppong.Entity.Puzzle;
import gocha.jjamppong.dto.MemberDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@Transactional
class PuzzleRepositoryTest {

    @Autowired
    private PuzzleRepository puzzleRepository;

    private Puzzle puzzle;

    @BeforeEach
    void beforeEach(){
        //given
        puzzle = Puzzle.builder()
                .title("title")
                .difficulty(40L)
                .content("content")
                .solution("solution")
                .image_path("/")
                .puzzle_code("1-1")
                .build();
    }



    @Test
    @DisplayName("퍼즐 저장 테스트")
    void save() {

        //when
        Long savedId = puzzleRepository.save(puzzle);

        //then
        assertEquals(puzzle.getId(), savedId);
    }

    @Test
    @DisplayName("id로 퍼즐 조회 테스트")
    void findbyId() {

        //when
        puzzleRepository.save(puzzle);

        Puzzle findbyIdMember = puzzleRepository.findOne(puzzle.getId());

        //then
        assertEquals(puzzle, findbyIdMember);
    }

    @Test
    @DisplayName("모든 퍼즐 조회 테스트")
    void findAll() {
        //given
        Puzzle puzzle1 = Puzzle.builder()
                .difficulty(40L)
                .content("content1")
                .solution("solution1")
                .image_path("/")
                .puzzle_code("1-1")
                .build();
        puzzleRepository.save(puzzle1);

        Puzzle puzzle2 = Puzzle.builder()
                .difficulty(40L)
                .content("content2")
                .solution("solution2")
                .image_path("/")
                .puzzle_code("1-2")
                .build();
        puzzleRepository.save(puzzle2);


        //when
        List<Puzzle> puzzles = puzzleRepository.findAll();

        //then
        assertEquals(puzzles.size(), 2);

    }
}