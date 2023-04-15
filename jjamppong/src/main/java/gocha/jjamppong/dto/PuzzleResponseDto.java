package gocha.jjamppong.dto;

import gocha.jjamppong.entity.Member;
import gocha.jjamppong.entity.Puzzle;
import gocha.jjamppong.entity.SolvedPuzzle;
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

    // Puzzle 엔티티를 dto로 변경
    public static PuzzleResponseDto toResponseDto(Puzzle puzzle){
        return PuzzleResponseDto.builder()
                .content(puzzle.getContent())
                .difficulty(puzzle.getDifficulty())
                .title(puzzle.getTitle())
                .puzzle_code(puzzle.getPuzzle_code())
                .id(puzzle.getId())
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
}
