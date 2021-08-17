package com.maven.community.service.serviceImpl;

import com.maven.community.dto.PaginationDto;
import com.maven.community.dto.QuestionDto;
import com.maven.community.mapper.QuestionMapper;
import com.maven.community.pojo.Question;
import com.maven.community.pojo.User;
import com.maven.community.service.QuestionService;
import com.maven.community.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author sunchuanjia
 * @Description
 * @create 2021-08-15 22:32
 */
@Service
public class QuestionServiceImpl implements QuestionService {
    @Autowired
    private QuestionMapper questionMapper;

    @Autowired
    private UserService userService;

    @Override
    public void createQuestion(Question question) {
        questionMapper.createQuestion(question);
    }

    @Override
    public List<Question> searchList() {
        return questionMapper.searchList();
    }

    @Override
    public List<QuestionDto> getQuestionDtoList() {
        List<Question> questions = questionMapper.searchList();
        List<QuestionDto> questionDtoList = new ArrayList<>();
        for (Question question : questions)
        {
           User user =  userService.findById(question.getCreator());
           questionDtoList.add(new QuestionDto(question, user));
        }
        return questionDtoList;
    }

    @Override
    public PaginationDto listPage(Integer page, Integer size) {
        PaginationDto paginationDto = new PaginationDto();
        Integer totalCount = selectCount();
        paginationDto.setPagination(totalCount, page, size);
        if (page < 1)
        {
            page = 1;
        }
        if (page > paginationDto.getTotalPages())
        {
            page = paginationDto.getTotalPages();
        }

        Integer offSet = size * (page - 1);
        List<Question> questionList = questionMapper.listPage(offSet, size);
        List<QuestionDto> questionDtoList = new ArrayList<>();
        for (Question question : questionList)
        {
            User user = userService.findById(question.getCreator());
            questionDtoList.add(new QuestionDto(question, user));
        }
        paginationDto.setQuestions(questionDtoList);
        return paginationDto;
    }

    @Override
    public Integer selectCount() {
        return questionMapper.getCount();
    }
}
