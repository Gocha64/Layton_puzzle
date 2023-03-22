package gocha.jjamppong.service;


import gocha.jjamppong.Entity.Member;
import gocha.jjamppong.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    @Transactional
    public Long register(Member member){
        memberRepository.save(member);
        return member.getId();
    }
}

