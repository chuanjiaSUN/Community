package com.maven.community.service;

import com.maven.community.dto.PaginationDto;
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

    /**
     * listPage 分页查询
     * @param page 分页起始页码
     * @param size 大小
     * @return list 分页查询结果
     * */
    PaginationDto listPage(Integer page, Integer size);

    /**
     * selectCount 获取数据库中question的总数
     * @return integer 总数
     * */
    Integer selectCount();

    /**
     * 查询出某个用户的问题
     * @param id 用户id
     * @param page 起始页码
     * @param size 分页大小
     * @return paginationDto 分页列表
     * */
    PaginationDto listUserQuestions(Integer id, Integer page, Integer size);

    /**
     * 查询用户问题
     * @param id 用户id
     * @return list 问题列表
     * */
    List<Question> selectById(Integer id);

    /**
     * getById 查询某人的提问
     * @param id user的accountId
     * @return questionDto
     * */
    QuestionDto getById(Integer id);
}
