package com.maven.community.mapper;

import com.maven.community.pojo.Question;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

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
}
