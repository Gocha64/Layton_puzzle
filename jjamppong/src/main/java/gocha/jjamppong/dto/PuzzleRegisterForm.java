package gocha.jjamppong.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
@Getter
public class PuzzleRegisterForm {

    String content;
    String puzzle_code;
    String title;
    String solution;
    String image_path;
    Long difficulty;

}
