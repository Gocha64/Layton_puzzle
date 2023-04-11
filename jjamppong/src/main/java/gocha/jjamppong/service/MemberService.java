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
    public Long register(MemberDto member){
        return memberRepository.save(member.toEntity());
    }


    //사용자 인증
    public String authenticate(LoginForm form){
        
        //사용자 정보를 가져옴
        //Member member = memberRepository.findbyName(form.getUserId());
        
        //사용자 인증
        


        return "SUCCESS";

    }

}

