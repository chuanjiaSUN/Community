package com.maven.community.controller;

import com.maven.community.dto.CommenCreatetDto;
import com.maven.community.dto.ResultDto;
import com.maven.community.exception.CustomizeErrorCode;
import com.maven.community.pojo.Comment;
import com.maven.community.pojo.User;
import com.maven.community.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * @author sunchuanjia
 * @Description
 * @create 2021-08-21 15:40
 */
@Controller
public class CommentController {

    @Autowired
    private CommentService commentService;
    /**
     * 使用requestBody注解，可将前端传递的json封装成后端使用的对象
     * @param commentDto 回复传输对象
     * @return object
     * */
    @ResponseBody
    @RequestMapping(value = "/comment", method = RequestMethod.POST)
    public Object post(@RequestBody CommenCreatetDto commentDto,
                       HttpServletRequest request)
    {
        User user = (User)request.getSession().getAttribute("user");
        if (user == null)
        {
            return ResultDto.errorOf(CustomizeErrorCode.NO_LOGIN);
        }
        Comment comment = new Comment();
        comment.setParentId(commentDto.getParentId());
        comment.setContent(commentDto.getContent());
        comment.setType(commentDto.getType());
        comment.setGmtCreat(System.currentTimeMillis());
        comment.setGmtModified(System.currentTimeMillis());
        comment.setCommentator(user.getId());
        comment.setLikeCount(0L);
        commentService.insert(comment);
        return ResultDto.okOf();
    }
}
