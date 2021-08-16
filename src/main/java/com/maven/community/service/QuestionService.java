package com.maven.community.service;

import com.maven.community.pojo.Question;

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
}
