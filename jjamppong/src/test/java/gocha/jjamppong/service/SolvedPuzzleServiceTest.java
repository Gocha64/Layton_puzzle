package gocha.jjamppong.service;

import gocha.jjamppong.entity.Member;
import gocha.jjamppong.entity.Puzzle;
import gocha.jjamppong.entity.SolvedPuzzle;
import gocha.jjamppong.dto.MemberDto;
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
    public void 퍼즐성공후등록() throws Exception {

    }


}