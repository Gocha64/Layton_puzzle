package gocha.jjamppong.entity;


import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Table(name = "solved_puzzle")
@NoArgsConstructor
public class SolvedPuzzle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "solved_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    @Setter
    private Member member;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "puzzle_id")
    @Setter
    private Puzzle puzzle;

    @Setter
    private Long score;


    @Builder
    public SolvedPuzzle(Member member, Puzzle puzzle, Long score) {
        this.member = member;
        this.puzzle = puzzle;
        this.score = score;

    }
}
