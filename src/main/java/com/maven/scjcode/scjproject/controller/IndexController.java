package com.maven.scjcode.scjproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author sunchuanjia
 * @Description
 * @create 2021-08-14 16:25
 */
@Controller
public class IndexController {
    @GetMapping("/")
    public String hello()
    {
        return "index";
    }
}
