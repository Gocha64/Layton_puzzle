package gocha.jjamppong.repository;


import gocha.jjamppong.entity.Member;
import gocha.jjamppong.entity.Puzzle;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {

    Member findByName(String name);
}
