package gocha.jjamppong.dto;

import gocha.jjamppong.entity.Member;
import gocha.jjamppong.entity.Puzzle;
import gocha.jjamppong.entity.SolvedPuzzle;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Slf4j
public class PuzzleResponseDto {
    String title;
    String content;
    String puzzle_code;
    String image_path;
    long difficulty;
    long id;

    // Puzzle 엔티티를 dto로 변경
    public static PuzzleResponseDto toResponseDto(Puzzle puzzle){
        return PuzzleResponseDto.builder()
                .content(puzzle.getContent())
                .difficulty(puzzle.getDifficulty())
                .title(puzzle.getTitle())
                .puzzle_code(puzzle.getPuzzle_code())
                .id(puzzle.getId())
                .image_path(puzzle.getImage_path())
                .build();
    }

    // SolvedPuzzle의 Puzzle 엔티티를 dto로 변경
    public static PuzzleResponseDto toResponseDto(SolvedPuzzle solvedPuzzle){
        return PuzzleResponseDto.builder()
                .content(solvedPuzzle.getPuzzle().getContent())
                .difficulty(solvedPuzzle.getPuzzle().getDifficulty())
                .title(solvedPuzzle.getPuzzle().getTitle())
                .puzzle_code(solvedPuzzle.getPuzzle().getPuzzle_code())
                .id(solvedPuzzle.getPuzzle().getId())
                .build();
    }

    public void ChangeContentNewlineTrim() {
        this.content = this.content.replaceAll("\\r\\n|\\r|\\n", "<br>");
        log.info(content);
    }
}
