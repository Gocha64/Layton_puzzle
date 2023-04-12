package gocha.jjamppong.dto;

import gocha.jjamppong.entity.Member;
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

    public Member toEntity(){
        return Member.builder()
                .username(name)
                .password(password)
                .cash(cash)
                .build();
    }


}
