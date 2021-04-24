package communication.cmm.service;


import communication.cmm.dto.QuestionDTO;
import communication.cmm.model.Question;

import java.util.List;

public interface QuestionService {
    void create(Question question);
    List<QuestionDTO> findAll();
}
