package gocha.jjamppong.repository;
import gocha.jjamppong.Entity.SolvedPuzzle;
import gocha.jjamppong.service.SolvedPuzzleService;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class SolvedPuzzleRepository {

    private final EntityManager em;

    public void save(SolvedPuzzle SolvedPuzzle) {em.persist(SolvedPuzzle);}

    public SolvedPuzzle findOne(Long id) {return em.find(SolvedPuzzle.class, id);}
}
