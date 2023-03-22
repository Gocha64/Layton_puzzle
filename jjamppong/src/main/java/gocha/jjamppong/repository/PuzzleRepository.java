package gocha.jjamppong.repository;
import gocha.jjamppong.Entity.Puzzle;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;



@Repository
@RequiredArgsConstructor
public class PuzzleRepository {

    private final EntityManager em;

    public void save(Puzzle puzzle) {em.persist(puzzle);}

    public Puzzle findOne(Long id){
        return em.find(Puzzle.class, id);
    }

}
