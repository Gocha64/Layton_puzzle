package gocha.jjamppong.entity;


import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@NoArgsConstructor
public class SolutionDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "solution_id")
    private Long id;

    private String content;

    private String image_path;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "puzzle_id")
    @Setter
    private Puzzle puzzle;

    @Builder
    public SolutionDetail(String content, String image_path, Puzzle puzzle){
        this.content = content;
        this.image_path = image_path;
        this.puzzle = puzzle;
    }




}
