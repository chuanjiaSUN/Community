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

    /**显示首页分页*/
    @Override
    public PaginationDto listPage(Integer page, Integer size) {
        PaginationDto paginationDto = new PaginationDto();
        Integer totalCount = selectCount();
        return setPagination(paginationDto, totalCount, page, size);
    }

    @Override
    public Integer selectCount() {
        return questionMapper.getCount();
    }

    /**显示单个user分页*/
    @Override
    public PaginationDto listUserQuestions(Integer id, Integer page, Integer size) {
        PaginationDto paginationDto = new PaginationDto();
        List<Question> userQuestions = selectById(id);
        paginationDto.setPagination(userQuestions.size(), page, size);
        if (page < 1)
        {
            page = 1;
        }
        if (page > paginationDto.getTotalPages())
        {
            page = paginationDto.getTotalPages();
        }
        Integer offSet = size * (page - 1);
        List<Question> questionList = questionMapper.listPageById(id, offSet, size);
        List<QuestionDto> questionDtoList = new ArrayList<>();
        User user = userService.findById(id);
        for (Question question : questionList)
        {
            questionDtoList.add(new QuestionDto(question, user));
        }
        paginationDto.setQuestions(questionDtoList);
        return paginationDto;
    }

    @Override
    public List<Question> selectById(Integer id) {
        return questionMapper.selectById(id);
    }

    @Override
    public QuestionDto getById(Integer id) {
        Question question = questionMapper.getById(id);
        Integer creator = question.getCreator();
        User user = userService.findById(creator);
        return new QuestionDto(question, user);
    }

    @Override
    public void createOrUpdate(Question question) {
        question.setGmtCreate(System.currentTimeMillis());
        question.setGmtModified(question.getGmtCreate());
        if (question.getId() == null)
        {
            //创建
            questionMapper.createQuestion(question);
        }else{
            //更新
            questionMapper.update(question);
        }
    }

    public PaginationDto setPagination(PaginationDto paginationDto, int totalCount, Integer page, Integer size)
    {
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




}
