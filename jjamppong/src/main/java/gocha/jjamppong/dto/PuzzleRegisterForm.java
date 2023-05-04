package gocha.jjamppong.dto;

import gocha.jjamppong.entity.Puzzle;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

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
    MultipartFile image;

    public Puzzle toEntity(){


        return Puzzle.builder()
                .title(title)
                .puzzle_code(puzzle_code)
                .content(content)
                .image_path(image_path)
                .difficulty(difficulty)
                .build();
    }

}
