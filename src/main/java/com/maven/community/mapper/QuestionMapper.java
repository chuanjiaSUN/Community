package com.maven.community.mapper;

import com.maven.community.dto.QuestionDto;
import com.maven.community.pojo.Question;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
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

    /**
     * listPage 分页查询
     * @param offSet 起始页码
     * @param size 大小
     * @return list 查询结果
     * */
    @Select("select * from question limit #{offSet}, #{size}")
    List<Question> listPage(Integer offSet, Integer size);

    /**
     * getCount 获取综述
     * @return integer
     * */
    @Select("select count(1) from question")
    Integer getCount();


    /**
     * 查询某人问题
     * @param id 用户id
     * @return list
     * */
    @Select("select * from question where creator=#{id}")
    List<Question> selectById(Integer id);

    /**
     * listPageById 用户分页查询
     * @param offSet 起始
     * @param size 大小
     * @param id userId
     * @return list 问题
     * */
    @Select("SELECT * FROM question WHERE creator = #{id} LIMIT #{offSet},#{size}")
    List<Question> listPageById(Integer id, Integer offSet, Integer size);

    /**
     * getById 查询某个问题
     * @param id 问题id
     * @return question
     * */
    @Select("select * from question where id = #{id}")
    Question getById(Integer id);
}
