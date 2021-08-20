package com.maven.community.interceptors;

import com.maven.community.pojo.User;
import com.maven.community.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author sunchuanjia
 * @Description 配置拦截器，添加登录检查
 * @create 2021-08-18 15:17
 */
@Slf4j
public class SessionInterceptor implements HandlerInterceptor{
    @Autowired
    private UserService userService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("SessionInterceptor + preHandle拦截的请求是： "+request.getRequestURI());

        //检查登录逻辑
        Cookie[] cookies = request.getCookies();
        if (cookies != null && cookies.length != 0)
        {
            for(Cookie cookie : cookies)
            {
                if ("token".equals(cookie.getName()))
                {
                    String token = cookie.getValue();
                    List<User> users = userService.findByToken(token);
                    if (users.size() != 0)
                    {
                        request.getSession().setAttribute("user", users.get(0));
                    }

                    break;
                }
            }
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        log.info("SessionInterceptor + postHandle拦截执行: " + modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        log.info("SessionInterceptor + afterCompletion执行异常: " + ex);
    }
}
