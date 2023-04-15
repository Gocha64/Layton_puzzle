package gocha.jjamppong.service;


import gocha.jjamppong.entity.Member;
import gocha.jjamppong.entity.Puzzle;
import gocha.jjamppong.entity.SolvedPuzzle;
import gocha.jjamppong.repository.PuzzleRepository;
import gocha.jjamppong.repository.SolvedPuzzleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

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

    //퍼즐을 이미 푼적 있는지 확인
    public boolean checkSolve(Member member, Puzzle puzzle){
        if (solvedPuzzleRepository.findByMemberAndPuzzle(member, puzzle).isEmpty())
            return false;
        return true;
    }

    public List<SolvedPuzzle> findAllbyMember(Member member){
        return solvedPuzzleRepository.findAllByMember(member);
    }

    // 퍼즐 페이징 처리
    public Page<SolvedPuzzle> findSolvedPuzzlesWithPaging(Member member, Pageable pageable) {
        return solvedPuzzleRepository.findAllByMember(member, pageable);
    }





}
