package gocha.jjamppong.service;


import gocha.jjamppong.dto.PuzzleAnswerSubmitForm;
import gocha.jjamppong.dto.PuzzleResponseDto;
import gocha.jjamppong.entity.Puzzle;
import gocha.jjamppong.repository.PuzzleRepository;
import gocha.jjamppong.wrapper.JsonPage;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
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
    @Cacheable(cacheNames = "AllPuzzleList")
    public List<Puzzle> findPuzzles() { return puzzleRepository.findAll();}

    // 퍼즐 페이징 처리
    @Cacheable(cacheNames = "puzzlePageList", key="'puzzlePage=' + #pageable.pageNumber")
    public JsonPage<PuzzleResponseDto> findPuzzlesWithPaging(Pageable pageable) {
        return new JsonPage<>(puzzleRepository.findAll(pageable).map(PuzzleResponseDto::toResponseDto));
    }
    
    // 퍼즐 단일 조회
    @Cacheable(cacheNames = "puzzle", key="'puzzled=' + #id")
    public Puzzle findOne(Long id) {return puzzleRepository.findById(id).orElse(new Puzzle());}

    // 퍼즐 정답 판별
    public Boolean checkAnswer(Long puzzleId, PuzzleAnswerSubmitForm form){
        String answer = puzzleRepository.findById(puzzleId).orElse(new Puzzle()).getSolution();


        if (form.getAnswer().equals(answer))
            return true;

        return false;
    }
}
