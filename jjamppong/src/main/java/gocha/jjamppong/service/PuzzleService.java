package gocha.jjamppong.service;


import gocha.jjamppong.Entity.Puzzle;
import gocha.jjamppong.repository.PuzzleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PuzzleService {

    private final PuzzleRepository puzzleRepository;

    @Transactional
    public Long register(Puzzle puzzle){
        puzzleRepository.save(puzzle);
        return puzzle.getId();
    }
}
