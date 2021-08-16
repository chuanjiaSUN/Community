package com.maven.community.controller;

import com.maven.community.pojo.User;
import com.maven.community.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * @author sunchuanjia
 * @Description
 * @create 2021-08-14 16:25
 */
@Controller
public class IndexController {

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String hello(HttpServletRequest request)
    {
        Cookie[] cookies = request.getCookies();
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
        return "index";
    }
}
