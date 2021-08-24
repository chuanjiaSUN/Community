package com.maven.community.mapper;

import com.maven.community.pojo.Comment;
import org.springframework.stereotype.Repository;

/**
 * @author sunchuanjia
 * @Description
 * @create 2021-08-24 16:18
 */
@Repository("CommentExtMapper")
public interface CommentExtMapper {
    /**
     * incCommentCount 增长评论数
     * @param record 评论
     * @return int 增长
     * */
    int incCommentCount(Comment record);
}
