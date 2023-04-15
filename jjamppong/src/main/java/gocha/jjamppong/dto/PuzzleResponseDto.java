package gocha.jjamppong.dto;

import gocha.jjamppong.entity.Member;
import gocha.jjamppong.entity.Puzzle;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class PuzzleResponseDto {
    String title;
    String content;
    String puzzle_code;
    long difficulty;
    long id;

    public static PuzzleResponseDto toResponseDto(Puzzle puzzle){
        return PuzzleResponseDto.builder()
                .content(puzzle.getContent())
                .difficulty(puzzle.getDifficulty())
                .title(puzzle.getTitle())
                .puzzle_code(puzzle.getPuzzle_code())
                .id(puzzle.getId())
                .build();
    }
}
