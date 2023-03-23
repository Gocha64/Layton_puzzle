package gocha.jjamppong.Entity;


import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    private String name;

    private String password;

    private String cash;

    @OneToMany(mappedBy = "member")
    private List<SolvedPuzzle> solved_puzzles;


    public void addSolved_puzzle(SolvedPuzzle solvedPuzzle){
        solved_puzzles.add(solvedPuzzle);
        solvedPuzzle.setMember(this);
    }

    @Builder
    public Member(String name, String password, String cash) {
        this.name = name;
        this.password = password;
        this.cash = cash;
        this.solved_puzzles = new ArrayList<>();
    }
}
