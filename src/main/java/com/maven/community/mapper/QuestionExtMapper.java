package com.maven.community.mapper;

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
}