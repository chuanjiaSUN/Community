package com.maven.community.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author sunchuanjia
 * @Description
 * @create 2021-08-17 22:15
 */
@Controller
public class ProfileController {

    private static final String PROFILE_QUESTIONS = "questions";
    private static final String PROFILE_REPLIES = "replies";

    @GetMapping("/profile/{action}")
    public String profile(@PathVariable(name = "action") String action,
                          Model model)
    {
        if (PROFILE_QUESTIONS.equals(action))
        {
            model.addAttribute("section","questions");
            model.addAttribute("sectionName","我的提问");
        }else if (PROFILE_REPLIES.equals(action))
        {
            model.addAttribute("section","replies");
            model.addAttribute("sectionName","最新回复");
        }
        return "profile";
    }
}
