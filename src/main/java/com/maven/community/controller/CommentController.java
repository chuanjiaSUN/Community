package com.maven.community.controller;

import com.maven.community.dto.CommentDto;
import com.maven.community.mapper.CommentMapper;
import com.maven.community.pojo.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.awt.*;

/**
 * @author sunchuanjia
 * @Description
 * @create 2021-08-21 15:40
 */
@Controller
public class CommentController {


    @Autowired
    private CommentMapper commentMapper;
    /**
     * 使用requestBody注解，可将前端传递的json封装成后端使用的对象
     * @param commentDto 回复传输对象
     * @return object
     * */
    @ResponseBody
    @RequestMapping(value = "/comment", method = RequestMethod.POST)
    public Object post(@RequestBody CommentDto commentDto)
    {
        Comment comment = new Comment();
        comment.setParentId(commentDto.getParentId());
        comment.setContent(commentDto.getContent());
        comment.setType(commentDto.getType());
        comment.setGmtCreat(System.currentTimeMillis());
        comment.setGmtModified(System.currentTimeMillis());
        comment.setCommentator(1);
        comment.setLikeCount(0L);
        commentMapper.insert(comment);
        return null;
    }
}
