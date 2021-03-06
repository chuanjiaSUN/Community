package com.maven.community.service;

import com.maven.community.dto.CommentDto;
import com.maven.community.enums.CommentTypeEnum;
import com.maven.community.pojo.Comment;
import com.maven.community.pojo.User;

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
     * @param user*/
    void insert(Comment comment, User user);

    /**
     * 根据Id查询问题
     * @param id 问题id
     * @param type
     * @return list 问题列表
     * */
    List<CommentDto> listByQuestionId(Long id, CommentTypeEnum type);
}
