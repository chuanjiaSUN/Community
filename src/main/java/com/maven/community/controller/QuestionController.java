package com.maven.community.controller;

import com.maven.community.dto.CommentDto;
import com.maven.community.dto.QuestionDto;
import com.maven.community.enums.CommentTypeEnum;
import com.maven.community.service.CommentService;
import com.maven.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;


/**
 * @author sunchuanjia
 * @Description
 * @create 2021-08-18 22:02
 */
@Controller
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @Autowired
    private CommentService commentService;

    @GetMapping("/question/{id}")
    public String question(@PathVariable(name = "id") Long id, Model model)
    {
        QuestionDto questionDto = questionService.getById(id);
        //CommentDto是后端传给前端的， CommentCreateDto是前端传给后端的
        List<CommentDto> comments = commentService.listByQuestionId(id, CommentTypeEnum.QUESTION);
        //累加阅读数
        questionService.incView(id);
        model.addAttribute("question", questionDto);
        model.addAttribute("comments", comments);
        return "question";
    }
}
