package gocha.jjamppong.service;


import gocha.jjamppong.entity.SolvedPuzzle;
import gocha.jjamppong.repository.SolvedPuzzleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class SolvedPuzzleService {

    private final SolvedPuzzleRepository solvedPuzzleRepository;

    //정답 처리시 해당 내용 등록
    @Transactional
    public Long register(SolvedPuzzle solvedPuzzle){
        solvedPuzzleRepository.save(solvedPuzzle);
        return solvedPuzzle.getId();
    }



}
