package com.maven.community.service.serviceImpl;

import com.maven.community.enums.CommentTypeEnum;
import com.maven.community.exception.CustomizeErrorCode;
import com.maven.community.exception.CustomizeException;
import com.maven.community.mapper.CommentMapper;
import com.maven.community.pojo.Comment;
import com.maven.community.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author sunchuanjia
 * @Description
 * @create 2021-08-21 16:25
 */
@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    private CommentMapper commentMapper;
    @Override
    public void insert(Comment comment) {
        if (comment.getParentId() == null || comment.getParentId() == 0)
        {
            throw new CustomizeException(CustomizeErrorCode.TARGET_PARAM_NOT_FOUND);
        }
        if (comment.getType() == null || !CommentTypeEnum.isExist(comment.getType()))
        {
            throw new CustomizeException(CustomizeErrorCode.TYPE_PARAM_WRONG);
        }
        if (comment.getType() == CommentTypeEnum.COMMENT.getType())
        {
            //回复评论
            Comment dbComment = commentMapper.selectByPrimaryKey(comment.getParentId());
            if (dbComment == null)
            {
                throw new CustomizeException(CustomizeErrorCode.COMMENT_NOT_FOUND);
            }
            commentMapper.insert(comment);
        }else {
            //回复问题

        }
        commentMapper.insert(comment);
    }
}
