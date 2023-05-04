package gocha.jjamppong.dto;

import gocha.jjamppong.entity.SolutionDetail;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Slf4j
public class SolutionResponseDto {

    String content;

    String image_path;


    public static SolutionResponseDto toResponseDto(SolutionDetail solutionDetail){
        return SolutionResponseDto.builder()
                .content(solutionDetail.getContent())
                .image_path(solutionDetail.getImage_path())
                .build();
    }

    public void ChangeContentNewlineTrim() {
        this.content = this.content.replaceAll("\\r\\n|\\r|\\n", "<br>");
        //log.info(content);
    }
}
