package gocha.jjamppong.service;

import gocha.jjamppong.Entity.Member;
import gocha.jjamppong.Entity.Puzzle;
import gocha.jjamppong.Entity.SolvedPuzzle;
import gocha.jjamppong.repository.MemberRepository;
import gocha.jjamppong.repository.PuzzleRepository;
import gocha.jjamppong.repository.SolvedPuzzleRepository;
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
@Rollback(false)
public class SolvedPuzzleServiceTest {
    @Autowired MemberService memberService;
    @Autowired MemberRepository memberRepository;
    @Autowired PuzzleService puzzleService;
    @Autowired PuzzleRepository puzzleRepository;
    @Autowired SolvedPuzzleService solvedPuzzleService;
    @Autowired SolvedPuzzleRepository solvedPuzzleRepository;

    @Test
    public void 퍼즐성공후등록() throws Exception{
        //given
        String name = "kim";
        String password = "1234";
        String cash = "0";
        Member member = new Member(name, password, cash);

        Long difficulty = 40L;
        String image_path = "/";
        String content = "content";
        String solution = "solution";
        String puzzle_code = "1-1";
        Puzzle puzzle = new Puzzle(difficulty, image_path, content, solution, puzzle_code);

        SolvedPuzzle solvedPuzzle = SolvedPuzzle.createSolvedPuzzle(member, puzzle, puzzle.getDifficulty());
        //when
        puzzleService.register(puzzle);
        memberService.register(member);
        Long id = solvedPuzzleService.register(solvedPuzzle);
        System.out.println("멤버" + member.getSolved_puzzles().get(0).getId());
        System.out.println("퍼즐" + puzzle.getSolved_puzzles().get(0).getId());

        //then
        assertEquals(solvedPuzzle, solvedPuzzleRepository.findOne(id));
    }


}