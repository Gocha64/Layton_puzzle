package gocha.jjamppong.service;

import gocha.jjamppong.entity.Puzzle;
import gocha.jjamppong.repository.PuzzleRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class PuzzleServiceTest {
    @InjectMocks
    private PuzzleService puzzleService;

    @Mock
    private PuzzleRepository puzzleRepository;

    @Test
    public void 퍼즐등록() throws Exception{
        //given
        Puzzle puzzle = Puzzle.builder()
                .title("title")
                .difficulty(40L)
                .content("content")
                .solution("solution")
                .image_path("/")
                .puzzle_code("1-1")
                .build();


        // mock
        when(puzzleRepository.save(any())).thenReturn(puzzle);
        when(puzzleRepository.findById(any())).thenReturn(Optional.ofNullable(puzzle));



        //when
        Long savedId = puzzleService.register(puzzle);

        //then
        assertEquals(puzzle, puzzleRepository.findById(savedId).get());
    }


}