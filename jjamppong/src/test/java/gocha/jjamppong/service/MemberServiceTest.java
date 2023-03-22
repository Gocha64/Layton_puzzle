package gocha.jjamppong.service;

import gocha.jjamppong.Entity.Member;
import gocha.jjamppong.Entity.SolvedPuzzle;
import gocha.jjamppong.repository.MemberRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

import static org.junit.Assert.*;


@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class MemberServiceTest {
    @Autowired MemberService memberService;
    @Autowired MemberRepository memberRepository;

    @Test
    public void 회원가입() throws Exception{
        //given
        String name = "kim";
        String password = "1234";
        String cash = "0";
        Member member = new Member(name, password, cash);

        //when
        Long savedId = memberService.register(member);

        //then
        assertEquals(member, memberRepository.findOne(savedId));
    }


}