package gocha.jjamppong.Entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Table(name = "solved_puzzle")
public class SolvedPuzzle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "solved_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "puzzle_id")
    private Puzzle puzzle;

    @Setter
    private Long score;


    public void setMember(Member member){
        this.member = member;
        member.getSolved_puzzles().add(this);
    }

    public void setPuzzle(Puzzle puzzle){
        this.puzzle = puzzle;
        puzzle.getSolved_puzzles().add(this);

    }

    public static SolvedPuzzle createSolvedPuzzle(Member member, Puzzle puzzle, Long score) {
        SolvedPuzzle solvedPuzzle = new SolvedPuzzle();
        solvedPuzzle.setMember(member);
        solvedPuzzle.setPuzzle(puzzle);
        solvedPuzzle.setScore(score);

        return solvedPuzzle;
    }
}
