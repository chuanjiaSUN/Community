package com.maven.community.controller;

import com.maven.community.dto.QuestionDto;
import com.maven.community.pojo.Question;
import com.maven.community.pojo.User;
import com.maven.community.service.QuestionService;
import com.maven.community.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

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
    private UserService userService;

    @Autowired
    private QuestionService questionService;

    @GetMapping("/")
    public String hello(HttpServletRequest request,
                        Model model)
    {
        Cookie[] cookies = request.getCookies();
        if (cookies != null && cookies.length != 0)
        {
            for(Cookie cookie : cookies)
            {
                if ("token".equals(cookie.getName()))
                {
                    String token = cookie.getValue();
                    User user = userService.findByToken(token);
                    if (user != null)
                    {
                        request.getSession().setAttribute("user", user);
                    }
                    break;
                }
            }
        }
        //转发到首页前，查询列表信息
        List<QuestionDto> questionDtoList = questionService.getQuestionDtoList();
        model.addAttribute("questions", questionDtoList);
        return "index";
    }
}
