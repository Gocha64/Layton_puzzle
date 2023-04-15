package gocha.jjamppong.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class LoginForm {

    // 입력받은 아이디
    private String username;

    // 입력받은 패스워드
    private String userPassword;
}
