package com.maven.community.service;

import com.maven.community.dto.CommentDto;
import com.maven.community.pojo.Comment;
import com.maven.community.pojo.CommentExample;

import java.util.List;

/**
 * @author sunchuanjia
 * @Description
 * @create 2021-08-21 16:25
 */
public interface CommentService {
    /**
     * insert 插入
     * @param comment 评论
     * */
    void insert(Comment comment);

    /**
     * 根据Id查询问题
     * @param id 问题id
     * @return list 问题列表
     * */
    List<CommentDto> listByQuestionId(Long id);
}
