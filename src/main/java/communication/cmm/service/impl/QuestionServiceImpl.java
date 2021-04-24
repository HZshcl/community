package communication.cmm.service.impl;

import communication.cmm.dto.QuestionDTO;
import communication.cmm.mapper.QuestionMapper;
import communication.cmm.mapper.UserMapper;
import communication.cmm.model.Question;
import communication.cmm.model.User;
import communication.cmm.service.QuestionService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionServiceImpl implements QuestionService {
    @Autowired(required=false)
    private QuestionMapper questionMapper;
    @Autowired(required=false)
    private UserMapper userMapper;
    @Override
    public void create(Question question) {
        questionMapper.create(question);
    }

    @Override
    public List<QuestionDTO> findAll() {
       List<Question> questions= questionMapper.findAll();
       List<QuestionDTO> questionDTOS=new ArrayList<>();
       for(Question question:questions){
           QuestionDTO questionDTO=new QuestionDTO();
           User user= userMapper.findById(question.getCreator());
//           beanUtils 将一个类中的属性值copy到另一个类中
           BeanUtils.copyProperties(question,questionDTO);
           questionDTO.setUser(user);
           questionDTOS.add(questionDTO);
       }
        return questionDTOS;
    }
}
