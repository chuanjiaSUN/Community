package com.maven.community.mapper;

import com.maven.community.dto.QuestionQueryDto;
import com.maven.community.pojo.Comment;
import com.maven.community.pojo.Question;
import com.maven.community.pojo.QuestionExample;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("QuestionExtMapper")
public interface QuestionExtMapper {
    /**
     * 增加阅读数
     * @param record 问题
     * @return int
     * */
    int incView(Question record);

    /**
     * incComment 增加回复数
     * @param record 问题
     * @return int
     * */
    int incComment(Question record);

    /**
     * selectRelated 查询标签相关的问题
     * @param record 问题
     * @return list 问题列表
     * */
    List<Question> selectRelated(Question record);

    /**
     * countBySearch 开启搜索时
     * @param questionQueryDto 查询例子
     * @return int 问题数
     * */
    Integer countBySearch(QuestionQueryDto questionQueryDto);

    /**
     * 根据questionQueryDto查询问题
     * @param questionQueryDto 分页查询封装对象
     * */
    List<Question> selectBySearch(QuestionQueryDto questionQueryDto);
}