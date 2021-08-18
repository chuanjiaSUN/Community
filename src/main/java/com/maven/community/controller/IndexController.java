package com.maven.community.controller;

import com.maven.community.dto.PaginationDto;
import com.maven.community.dto.QuestionDto;
import com.maven.community.pojo.Question;
import com.maven.community.pojo.User;
import com.maven.community.service.QuestionService;
import com.maven.community.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * @author sunchuanjia
 * @Description
 * @create 2021-08-14 16:25
 */
@Controller
public class IndexController {


    @Autowired
    private QuestionService questionService;

    @GetMapping("/")
    public String hello(HttpServletRequest request,
                        Model model,
                        @RequestParam(name = "page", defaultValue = "1") Integer page,
                        @RequestParam(name = "size", defaultValue = "5") Integer size)
    {
        //转发到首页前，查询列表信息
/*       List<QuestionDto> questionDtoList = questionService.getQuestionDtoList();**/
        PaginationDto pagination = questionService.listPage(page, size);
        model.addAttribute("pagination", pagination);
        return "index";
    }
}
