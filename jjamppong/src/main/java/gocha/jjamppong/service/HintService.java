package gocha.jjamppong.service;


import gocha.jjamppong.Entity.Hint;
import gocha.jjamppong.repository.HintRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class HintService {

    private final HintRepository hintRepository;

    @Transactional
    public Long register(Hint hint){
        hintRepository.save(hint);
        return hint.getId();
    }
}
