package com.maven.community.service;

import com.maven.community.pojo.Comment;

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
}
