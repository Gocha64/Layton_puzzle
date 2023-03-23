package gocha.jjamppong.Entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
public class Hint {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "hint_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "puzzle_id")
    @Setter
    private Puzzle puzzle;

    private String content;

    private String image_path;

    //힌트의 단계
    private int level;

    @Builder
    public Hint (String content, String image_path, int level){
        this.content = content;
        this.image_path = image_path;
        this.level = level;

    }

}
