package com.maven.community.service;

import com.maven.community.dto.QuestionDto;
import com.maven.community.pojo.Question;

import java.util.List;

/**
 * @author sunchuanjia
 * @Description
 * @create 2021-08-15 22:31
 */
public interface QuestionService {
    /**
     * createQuestion
     * @param question 发起的问题
     * */
    void createQuestion(Question question);

    /**
     * searchList 查询问题列表
     * @return list 问题列表
     * */
    List<Question> searchList();

    /**
     * getQuestionDtoList 获取传输对象
     * @return list 传输对象列表
     * */
    List<QuestionDto> getQuestionDtoList();
}