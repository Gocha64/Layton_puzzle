package gocha.jjamppong.repository;

import gocha.jjamppong.entity.Member;
import gocha.jjamppong.dto.MemberDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doReturn;

@SpringBootTest
@Transactional
class MemberRepositoryTest {

    @Autowired
    private MemberRepository memberRepository;

    private Member member;

    @BeforeEach
    void beforeEach(){
        //given
        MemberDto memberDto = new MemberDto("테스트이름", "테스트pw", "테스트cash");
        member = memberDto.toEntity();
    }



    @Test
    @DisplayName("회원 저장 테스트")
    void save() {

        //when
        Long savedId = memberRepository.save(member);

        //then
        assertEquals(member.getId(), savedId);
    }

    @Test
    @DisplayName("id로 회원 조회 테스트")
    void findbyId() {

        //when
        memberRepository.save(member);

        Member findbyIdMember = memberRepository.findOne(member.getId());

        //then
        assertEquals(member, findbyIdMember);
    }

    @Test
    @DisplayName("name으로 회원 조회 테스트")
    void findbyName(){
        //when
        memberRepository.save(member);

        Member findbyNameMember = memberRepository.findbyName(member.getName());

        //then
        assertEquals(member, findbyNameMember);

    }

    @Test
    @DisplayName("모든 회원 조회 테스트")
    void findAll() {
        //given
        MemberDto memberDto1 = new MemberDto("테스트이름1", "테스트pw1", "테스트cash1");
        Member member1 = memberDto1.toEntity();
        memberRepository.save(member1);

        MemberDto memberDto2 = new MemberDto("테스트이름2", "테스트pw2", "테스트cash2");
        Member member2 = memberDto2.toEntity();
        memberRepository.save(member2);


        //when
        List<Member> members = memberRepository.findAll();

        //then
        assertEquals(members.size(), 2);

    }
}