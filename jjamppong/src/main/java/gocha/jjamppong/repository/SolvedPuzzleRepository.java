package gocha.jjamppong.repository;
import gocha.jjamppong.entity.Member;
import gocha.jjamppong.entity.Puzzle;
import gocha.jjamppong.entity.SolvedPuzzle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SolvedPuzzleRepository extends JpaRepository<SolvedPuzzle, Long> {

    Optional<SolvedPuzzle> findById(Long id);
    List<SolvedPuzzle> findAllByPuzzle(Puzzle puzzle);
    List<SolvedPuzzle> findAllByMember(Member Member);
    List<SolvedPuzzle> findByMemberAndPuzzle(Member Member, Puzzle puzzle);

//    private final EntityManager em;
//
//    public void save(SolvedPuzzle SolvedPuzzle) {em.persist(SolvedPuzzle);}
//
//    public SolvedPuzzle findOne(Long id) {return em.find(SolvedPuzzle.class, id);}
//
//    //퍼즐을 맞춘 사용자 조회
//    public List<SolvedPuzzle> findAllbyPuzzleId(Long puzzleId){
//        return em.createQuery("select s from SolvedPuzzle s " +
//                "where s.puzzle.id = :puzzleId ", SolvedPuzzle.class)
//                .setParameter("puzzleId", puzzleId)
//                .getResultList();
//    }
//
//    //사용자가 맞춘 퍼즐 조회
//    public List<SolvedPuzzle> findAllbyMemberId(Long memberId){
//        return em.createQuery("select s from SolvedPuzzle s " +
//                        "where s.member.id = :memberId ", SolvedPuzzle.class)
//                .setParameter("memberId", memberId)
//                .getResultList();
//    }
//
//    //퍼즐 id와 멤버 id로 해당하는 이력이 있는지 조회
//    public List<SolvedPuzzle> findbyMemberIdAndPuzzleId(Long memberId, Long puzzleId){
//        return em.createQuery(
//                "SELECT s " +
//                        "FROM SolvedPuzzle s " +
//                        "where s.member.id = :memberId and s.puzzle.id = :puzzleId", SolvedPuzzle.class)
//                .setParameter("puzzleId", puzzleId)
//                .setParameter("memberId", memberId)
//                .getResultList();
//
//    }
}
