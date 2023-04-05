package gocha.jjamppong.service;


import gocha.jjamppong.entity.Puzzle;
import gocha.jjamppong.repository.PuzzleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
    
    // 퍼즐 전체 조회
    public List<Puzzle> findPuzzles() { return puzzleRepository.findAll();}
    
    // 퍼즐 단일 조회
    public Puzzle findOne(Long id) {return puzzleRepository.findOne(id);}
}
