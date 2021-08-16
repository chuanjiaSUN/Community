package com.maven.community.service.serviceImpl;

import com.maven.community.mapper.QuestionMapper;
import com.maven.community.pojo.Question;
import com.maven.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author sunchuanjia
 * @Description
 * @create 2021-08-15 22:32
 */
@Service
public class QuestionServiceImpl implements QuestionService {
    @Autowired
    private QuestionMapper questionMapper;
    @Override
    public void createQuestion(Question question) {
        questionMapper.createQuestion(question);
    }
}
