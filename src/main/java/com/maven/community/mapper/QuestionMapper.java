package com.maven.community.mapper;

import com.maven.community.pojo.Question;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author sunchuanjia
 * @Description
 * @create 2021-08-15 22:24
 */
@Mapper
@Repository("QuestionMapper")
public interface QuestionMapper {

    /**
     * createQuestion
     * @param question 输入的问题
     * */
    @Insert("insert into question (title,description,gmt_create,gmt_modified,creator,tag) values (#{title}, #{description}, #{gmtCreate}, #{gmtModified}, #{creator}, #{tag})")
    void createQuestion(Question question);

    /**
     * searchList 查询问题列表
     * @return list 问题列表
     * */
    @Select("select * from question")
    List<Question> searchList();
}
