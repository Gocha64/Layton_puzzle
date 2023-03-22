package gocha.jjamppong.Entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
public class Puzzle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "puzzle_id")
    private Long id;

    @OneToMany(mappedBy = "puzzle")
    private List<SolvedPuzzle> solved_puzzles = new ArrayList<>();

    private Long difficulty;

    private String image_path;

    private String content;

    private String solution;

    private String puzzle_code;

    public void addSolved_puzzle(SolvedPuzzle solvedPuzzle){
        solved_puzzles.add(solvedPuzzle);
        solvedPuzzle.setPuzzle(this);
    }

    public Puzzle(Long difficulty, String image_path, String content, String solution, String puzzle_code) {

        this.difficulty = difficulty;
        this.image_path = image_path;
        this.content = content;
        this.solution = solution;
        this.puzzle_code = puzzle_code;
        this.solved_puzzles = new ArrayList<>();
    }
}
