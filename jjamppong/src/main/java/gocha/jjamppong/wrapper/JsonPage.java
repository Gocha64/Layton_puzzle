package gocha.jjamppong.wrapper;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.List;

// 캐싱을 위해 Page 클래스를 기본 생성자가 있는 클래스로 래핑함
@JsonIgnoreProperties(ignoreUnknown = true, value = {"pageable"})
public class JsonPage<T> extends PageImpl<T> {
    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public JsonPage(@JsonProperty("content") List<T> content,
                    @JsonProperty("number") int page,
                    @JsonProperty("size") int size,
                    @JsonProperty("totalElements") long total) {
        super(content, PageRequest.of(page, size), total);
    }

    public JsonPage(Page<T> page) {
        super(page.getContent(),
                page.getPageable(),
                page.getTotalElements());
    }
}