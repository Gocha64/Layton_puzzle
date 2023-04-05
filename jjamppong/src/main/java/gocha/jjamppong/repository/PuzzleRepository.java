package gocha.jjamppong.repository;
import gocha.jjamppong.entity.Puzzle;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
@RequiredArgsConstructor
public class PuzzleRepository {

    private final EntityManager em;

    public Long save(Puzzle puzzle) {
        em.persist(puzzle);
        return puzzle.getId();
    }

    public Puzzle findOne(Long id){
        return em.find(Puzzle.class, id);
    }

    public List<Puzzle> findAll(){
        return em.createQuery("select p from Puzzle p", Puzzle.class)
                .getResultList();

    }

}
