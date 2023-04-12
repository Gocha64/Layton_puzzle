package gocha.jjamppong.service;

import gocha.jjamppong.controller.LoginForm;
import gocha.jjamppong.dto.MemberDto;
import gocha.jjamppong.entity.Member;
import gocha.jjamppong.repository.MemberRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    //회원가입
    @Transactional
    public Long register(MemberDto memberDto){
        Member member = memberDto.toEntity();
        memberRepository.save(member);
        return member.getId();
    }

    public Member findByName(String name){
        return memberRepository.findByName(name);
    }


    //사용자 인증
    public String authenticate(LoginForm form){
        
        //사용자 정보를 가져옴
        Member member = findByName(form.getUserId());
        
        //사용자 인증
        if (member == null || !member.getPassword().equals(form.getUserPassword())){
            return "FAIL";
        }
        


        return "SUCCESS";

    }

}

