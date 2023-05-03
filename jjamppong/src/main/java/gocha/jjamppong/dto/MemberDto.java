package gocha.jjamppong.dto;

import gocha.jjamppong.entity.Member;
import gocha.jjamppong.enums.UserAuthority;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class MemberDto {

    String name;
    String password;
    String cash;
    UserAuthority authority;

    public Member toEntity(){
        return Member.builder()
                .username(name)
                .password(password)
                .cash(cash)
                .authority(authority)
                .build();
    }


}
