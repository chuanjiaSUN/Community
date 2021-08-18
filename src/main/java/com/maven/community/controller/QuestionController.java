package com.maven.community.controller;

import com.maven.community.dto.QuestionDto;
import com.maven.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


/**
 * @author sunchuanjia
 * @Description
 * @create 2021-08-18 22:02
 */
@Controller
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @GetMapping("/question/{id}")
    public String question(@PathVariable(name = "id") Integer id,
                           Model model)
    {
        QuestionDto questionDto = questionService.getById(id);
        model.addAttribute("question", questionDto);
        return "question";
    }
}
