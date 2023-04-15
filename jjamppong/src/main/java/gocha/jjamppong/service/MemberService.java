package gocha.jjamppong.service;

import gocha.jjamppong.dto.MemberDto;
import gocha.jjamppong.entity.Member;
import gocha.jjamppong.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService implements UserDetailsService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Member member = memberRepository.findByUsername(username);


        List<GrantedAuthority> authorities = new ArrayList<>();
        if (member.getUsername().equals("Admin"))
            authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
        else
            authorities.add(new SimpleGrantedAuthority("ROLE_USER"));

        return new User(member.getUsername(), member.getPassword(), authorities);
    }

    //회원가입
    @Transactional
    public Long register(MemberDto memberDto){

        if(memberRepository.findByUsername(memberDto.getName()) == null){
            String p = passwordEncoder.encode(memberDto.getPassword());
            memberDto.setPassword(p);
            Member member = memberDto.toEntity();
            memberRepository.save(member);
            return member.getId();
        }

        return -1l;


    }


    public Member findByName(String name){
        return memberRepository.findByUsername(name);
    }


}

