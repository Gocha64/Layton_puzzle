package gocha.jjamppong.service;

import gocha.jjamppong.entity.Member;
import gocha.jjamppong.dto.MemberDto;
import gocha.jjamppong.repository.MemberRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;


@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class MemberServiceTest {
    @Autowired MemberService memberService;

    @MockBean
    MemberRepository memberRepository;

    Member member;

    @Test
    public void 회원가입() throws Exception{

        //given
        MemberDto memberDto = new MemberDto("kim", "1234", "0");
        member = memberDto.toEntity();


        //when
        memberService.register(memberDto);

        //then
        verify(memberRepository).save(any());
    }


}