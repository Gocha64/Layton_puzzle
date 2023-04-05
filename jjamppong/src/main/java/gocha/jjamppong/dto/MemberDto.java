package gocha.jjamppong.dto;

import gocha.jjamppong.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MemberDto {

    String name;
    String password;
    String cash;

    public Member toEntity(){
        return Member.builder()
                .name(name)
                .password(password)
                .cash(cash)
                .build();
    }


}
