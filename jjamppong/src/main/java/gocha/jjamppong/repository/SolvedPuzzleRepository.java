package gocha.jjamppong.repository;
import gocha.jjamppong.entity.SolvedPuzzle;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class SolvedPuzzleRepository {

    private final EntityManager em;

    public void save(SolvedPuzzle SolvedPuzzle) {em.persist(SolvedPuzzle);}

    public SolvedPuzzle findOne(Long id) {return em.find(SolvedPuzzle.class, id);}

    //퍼즐을 맞춘 사용자 조회
    public List<SolvedPuzzle> findAllbyPuzzleId(Long puzzleId){
        return em.createQuery("select s from SolvedPuzzle s " +
                "where s.puzzle.id = :puzzleId ", SolvedPuzzle.class)
                .setParameter("puzzleId", puzzleId)
                .getResultList();
    }

    //사용자가 맞춘 퍼즐 조회
    public List<SolvedPuzzle> findAllbyMemberId(Long memberId){
        return em.createQuery("select s from SolvedPuzzle s " +
                        "where s.member.id = :memberId ", SolvedPuzzle.class)
                .setParameter("memberId", memberId)
                .getResultList();
    }

    //퍼즐 id와 멤버 id로 해당하는 이력이 있는지 조회
    public List<SolvedPuzzle> findbyMemberIdNPuzzleId(Long memberId, Long puzzleId){
        return em.createQuery(
                "SELECT s " +
                        "FROM SolvedPuzzle s " +
                        "where s.member.id = :memberId and s.puzzle.id = :puzzleId", SolvedPuzzle.class)
                .setParameter("puzzleId", puzzleId)
                .setParameter("memberId", memberId)
                .getResultList();

    }
}
