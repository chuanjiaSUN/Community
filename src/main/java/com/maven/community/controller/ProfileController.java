package com.maven.community.controller;

import com.maven.community.dto.PaginationDto;
import com.maven.community.pojo.User;
import com.maven.community.service.NotificationService;
import com.maven.community.service.QuestionService;
import com.maven.community.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

/**
 * @author sunchuanjia
 * @Description
 * @create 2021-08-17 22:15
 */
@Controller
public class ProfileController {

    private static final String PROFILE_QUESTIONS = "questions";
    private static final String PROFILE_REPLIES = "replies";
    @Autowired
    private QuestionService questionService;

    @Autowired
    private NotificationService notificationService;

    @GetMapping("/profile/{action}")
    public String profile(@PathVariable(name = "action") String action,
                          Model model,
                          HttpServletRequest request,
                          @RequestParam(name = "page", defaultValue = "1") Integer page,
                          @RequestParam(name = "size", defaultValue = "5") Integer size)
    {
        //校验用户是否登录, 已经通过配置的 拦截器 将 user 写进了 session
        User user = (User) request.getSession().getAttribute("user");
        if (user == null)
        {
            return "redirect:/";
        }

        if (PROFILE_QUESTIONS.equals(action))
        {
            model.addAttribute("section","questions");
            model.addAttribute("sectionName","我的提问");
            PaginationDto paginationDto = questionService.listUserQuestions(user.getId(), page, size);
            model.addAttribute("pagination", paginationDto);
        }else if (PROFILE_REPLIES.equals(action))
        {
            PaginationDto paginationDto = notificationService.listUserNotifications(user.getId(), page, size);
            Long unreadCount = notificationService.unreadCount(user.getId());
            model.addAttribute("pagination", paginationDto);
            model.addAttribute("section","replies");
            model.addAttribute("unreadCount",unreadCount);
            model.addAttribute("sectionName","最新回复");
        }

        return "profile";
    }
}
