package gocha.jjamppong.service;


import gocha.jjamppong.controller.PuzzleAnswerSubmitForm;
import gocha.jjamppong.entity.Puzzle;
import gocha.jjamppong.repository.PuzzleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    // 퍼즐 페이징 처리
    public Page<Puzzle> findPuzzlesWithPaging(Pageable pageable) {
        return puzzleRepository.findAll(pageable);
    }
    
    // 퍼즐 단일 조회
    public Puzzle findOne(Long id) {return puzzleRepository.findById(id).orElse(new Puzzle());}

    // 퍼즐 정답 판별
    public Boolean checkAnswer(Long puzzleId, PuzzleAnswerSubmitForm form){
        String answer = puzzleRepository.findById(puzzleId).orElse(new Puzzle()).getSolution();


        if (form.getAnswer().equals(answer))
            return true;

        return false;
    }
}
