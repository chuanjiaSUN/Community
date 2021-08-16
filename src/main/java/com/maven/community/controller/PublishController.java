package com.maven.community.controller;

import com.maven.community.pojo.Question;
import com.maven.community.pojo.User;
import com.maven.community.service.QuestionService;
import com.maven.community.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * @author sunchuanjia
 * @Description
 * @create 2021-08-15 21:31
 */
@Controller
@Slf4j
public class PublishController {

    @Autowired
    private QuestionService questionService;
    @Autowired
    private UserService userService;

    @GetMapping(value = "/publish")
    public String publish()
    {
        return "publish";
    }

    @PostMapping("/publish")
    public String doPublish(
            @RequestParam(value = "title", required = false) String title,
            @RequestParam(value = "description", required = false) String description,
            @RequestParam(value = "tag", required = false) String tag,
            HttpServletRequest request,
            Model model
    )
    {
        model.addAttribute("title", title);
        model.addAttribute("description", description);
        model.addAttribute("tag", tag);
        if (title == null || "".equals(title))
        {
            model.addAttribute("error", "标题不能为空");
            return "publish";
        }
        if (description == null || "".equals(description))
        {
            model.addAttribute("error", "问题补充不能为空");
            return "publish";
        }
        if (tag == null || "".equals(tag))
        {
            model.addAttribute("error", "标签不能为空");
            return "publish";
        }
        User user = null;
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies)
        {
            if ("token".equals(cookie.getName()))
            {
                String token = cookie.getValue();
                user = userService.findByToken(token);
                if (user != null)
                {
                    request.getSession().setAttribute("user", user);
                }
                break;
            }
        }
        if (user == null)
        {
            model.addAttribute("error", "用户未登录");
            return "publish";
        }
        Question question = new Question();
        question.setTitle(title);
        question.setDescription(description);
        question.setTag(tag);
        question.setCreator(user.getId());
        question.setGmtCreate(System.currentTimeMillis());
        question.setGmtModified(question.getGmtCreate());
        questionService.createQuestion(question);
        return "redirect:/";
    }
}
