package gocha.jjamppong.repository;


import gocha.jjamppong.Entity.Hint;
import gocha.jjamppong.Entity.Puzzle;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class HintRepository {
    private final EntityManager em;

    public void save(Hint hint) {em.persist(hint);}

    public Hint findOne(Long id){
        return em.find(Hint.class, id);
    }

    //퍼즐에 해당하는 모든 힌트 불러오기
    public List<Hint> findAllbyPuzzleId(Long puzzleId){
        return em.createQuery("select h from Hint h " +
                "where h.puzzle.id = :puzzleId ", Hint.class)
                .setParameter("puzzleId", puzzleId)
                .getResultList();
    }



}
