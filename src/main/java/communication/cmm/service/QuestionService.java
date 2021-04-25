package communication.cmm.service;



import communication.cmm.model.Question;

import java.util.List;

public interface QuestionService {
    void create(Question question);
    List<Question> findAllBypage(Integer page,Integer size);
}
