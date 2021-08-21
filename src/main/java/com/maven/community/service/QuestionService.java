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
    PaginationDto listUserQuestions(Long id, Integer page, Integer size);

    /**
     * 查询用户问题
     * @param id 用户id
     * @return list 问题列表
     * */
    List<Question> selectById(Long id);

    /**
     * getById 查询某人的提问
     * @param id user的accountId
     * @return questionDto
     * */
    QuestionDto getById(Long id);

    /**
     * 插入或更新question表
     * @param question 编辑的问题
     * */
    void createOrUpdate(Question question);

    /**
     *incView 累积回复数
     * @param id 累加阅读数
     * */
    void incView(Long id);

    /**
     * selectByPrimaryKey 查找问题
     * @param parentId 回复的id
     * @return question 回复
     * */
    Question selectByPrimaryKey(Long parentId);

    /**
     * incCommentCount 增加回复数
     * @param question 问题
     * */
    void incCommentCount(Question question);
}
