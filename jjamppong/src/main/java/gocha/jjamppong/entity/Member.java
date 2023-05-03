package gocha.jjamppong.entity;


import gocha.jjamppong.enums.UserAuthority;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
@NoArgsConstructor
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    private String username;

    private String password;

    private String cash;

    @OneToMany(mappedBy = "member")
    private List<SolvedPuzzle> solved_puzzles;

    @Enumerated(EnumType.STRING)
    private UserAuthority authority;


    public void addSolved_puzzle(SolvedPuzzle solvedPuzzle){
        solved_puzzles.add(solvedPuzzle);
        solvedPuzzle.setMember(this);
    }

    @Builder
    public Member(String username, String password, String cash, UserAuthority authority) {
        this.username = username;
        this.password = password;
        this.cash = cash;
        this.solved_puzzles = new ArrayList<>();
        this.authority = authority;
    }
}
