package com.maven.community.service.serviceImpl;

import com.maven.community.dto.CommentDto;
import com.maven.community.enums.CommentTypeEnum;
import com.maven.community.exception.CustomizeErrorCode;
import com.maven.community.exception.CustomizeException;
import com.maven.community.mapper.CommentMapper;
import com.maven.community.pojo.*;
import com.maven.community.service.CommentService;
import com.maven.community.service.QuestionService;
import com.maven.community.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author sunchuanjia
 * @Description
 * @create 2021-08-21 16:25
 */
@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    private CommentMapper commentMapper;
    @Autowired
    private QuestionService questionService;
    @Autowired
    private UserService userService;

    @Transactional(rollbackFor = Exception.class)
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
            Question question = questionService.selectByPrimaryKey(comment.getParentId());
            if (question == null)
            {
                throw new CustomizeException(CustomizeErrorCode.QUESTION_NOT_FOUND);
            }
            commentMapper.insert(comment);
            question.setCommentCount(1);
            questionService.incCommentCount(question);
        }
    }

    @Override
    public List<CommentDto> listByQuestionId(Long id) {
        CommentExample commentExample = new CommentExample();
        commentExample.createCriteria()
                .andParentIdEqualTo(id)
                .andTypeEqualTo(CommentTypeEnum.QUESTION.getType());
        List<Comment> comments = commentMapper.selectByExample(commentExample);

        if (comments.size() == 0)
        {
            return new ArrayList<CommentDto>();
        }
        //获取去重的评论人
        Set<Long> commentators = comments.stream().map(comment -> comment.getCommentator()).collect(Collectors.toSet());
        //转换成userId 的map
        List<Long> userIds = new ArrayList<>();
        userIds.addAll(commentators);
        UserExample example = new UserExample();
        example.createCriteria()
                .andIdIn(userIds);
        List<User> users = userService.selectByExample(example);
        Map<Long, User> userMap = users.stream().collect(Collectors.toMap(user -> user.getId(), user -> user));
        //comment 转换成 commentDto
        List<CommentDto> commentDtos = comments.stream().map(comment -> {
            CommentDto commentDto = new CommentDto();
            BeanUtils.copyProperties(comment, commentDto);
            commentDto.setUser(userMap.get(comment.getCommentator()));
            return commentDto;
        }).collect(Collectors.toList());

        return commentDtos;
    }
}
