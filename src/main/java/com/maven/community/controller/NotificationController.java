package com.maven.community.controller;

import com.maven.community.dto.NotificationDto;
import com.maven.community.dto.PaginationDto;
import com.maven.community.enums.NotificationTypeEnum;
import com.maven.community.pojo.User;
import com.maven.community.service.NotificationService;
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
 * @create 2021-08-25 18:17
 */
@Controller
public class NotificationController {
    @Autowired
    private NotificationService notificationService;

    @GetMapping("/notification/{id}")
    public String profile(@PathVariable(name = "id") Long id, HttpServletRequest request)
    {
        //校验用户是否登录, 已经通过配置的 拦截器 将 user 写进了 session
        User user = (User) request.getSession().getAttribute("user");
        if (user == null)
        {
            return "redirect:/";
        }
        NotificationDto notificationDto = notificationService.read(id, user);
        if (NotificationTypeEnum.REPLY_COMMENT.getType() == notificationDto.getType()
        || NotificationTypeEnum.REPLY_QUESTION.getType() == notificationDto.getType())
        {
            return "redirect:/question/" + notificationDto.getOuterid();
        }else{
            return "redirect:/";
        }
    }
}
