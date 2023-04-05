package gocha.jjamppong.service;


import gocha.jjamppong.entity.Hint;
import gocha.jjamppong.entity.Member;
import gocha.jjamppong.entity.Puzzle;
import gocha.jjamppong.entity.SolvedPuzzle;
import gocha.jjamppong.dto.MemberDto;
import gocha.jjamppong.repository.HintRepository;
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

import static org.junit.Assert.assertEquals;


@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
@Rollback(value = false)
//퍼즐/힌트/멤버/정답처리 복합 테스트
public class PuzzleComplexTest {
    @Autowired
    MemberService memberService;
    @Autowired
    MemberRepository memberRepository;
    @Autowired
    PuzzleService puzzleService;
    @Autowired
    PuzzleRepository puzzleRepository;

    @Autowired
    HintRepository hintRepository;
    @Autowired
    HintService hintService;

    @Autowired SolvedPuzzleService solvedPuzzleService;
    @Autowired
    SolvedPuzzleRepository solvedPuzzleRepository;


    @Test
    public void 복합() throws Exception{
        //회원가입

        String name = "kim";
        String password = "1234";
        String cash = "0";
        MemberDto memberDto = new MemberDto(name, password, cash);

        //when
        Long memberId = memberService.register(memberDto);


        Member member = memberRepository.findOne(memberId);


        //퍼즐 등록
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

        puzzleService.register(puzzle);



        //힌트 등록
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

        puzzle.addHint(hint1);
        puzzle.addHint(hint2);
        puzzle.addHint(hint3);

        hintService.register(hint1);
        hintService.register(hint2);
        hintService.register(hint3);

        //정답처리
        SolvedPuzzle solvedPuzzle = SolvedPuzzle.builder()
                .score(puzzle.getDifficulty())
                .build();
        puzzle.addSolved_puzzle(solvedPuzzle);
        member.addSolved_puzzle(solvedPuzzle);

        solvedPuzzleService.register(solvedPuzzle);



    }
}
