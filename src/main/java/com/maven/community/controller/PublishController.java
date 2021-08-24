package com.maven.community.controller;

import com.maven.community.cache.TagCache;
import com.maven.community.dto.QuestionDto;
import com.maven.community.pojo.Question;
import com.maven.community.pojo.User;
import com.maven.community.service.QuestionService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/publish/{id}")
    public String edit(@PathVariable(name = "id") Long id,
                       Model model)
    {
        QuestionDto questionById = questionService.getById(id);
        model.addAttribute("title", questionById.getQuestion().getTitle());
        model.addAttribute("description", questionById.getQuestion().getDescription());
        model.addAttribute("tag", questionById.getQuestion().getTag());
        model.addAttribute("id", questionById.getQuestion().getId());
        model.addAttribute("tags", TagCache.get());

        return "publish";
    }

    @GetMapping(value = "/publish")
    public String publish(Model model)
    {
        model.addAttribute("tags", TagCache.get());
        return "publish";
    }

    @PostMapping("/publish")
    public String doPublish(
            @RequestParam(value = "title", required = false) String title,
            @RequestParam(value = "description", required = false) String description,
            @RequestParam(value = "tag", required = false) String tag,
            @RequestParam(value = "id", required = false) Long id,
            HttpServletRequest request,
            Model model
    )
    {
        model.addAttribute("title", title);
        model.addAttribute("description", description);
        model.addAttribute("tag", tag);
        model.addAttribute("tags", TagCache.get());
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
        String invalid = TagCache.filterInvalid(tag);
        if (StringUtils.isNotBlank(invalid))
        {
            model.addAttribute("error", " 输入非法标签" + invalid);
            return "publish";
        }
        //校验用户是否登录, 已经通过配置的 拦截器 将 user 写进了 session
        User user = (User) request.getSession().getAttribute("user");
        if (user == null)
        {
            model.addAttribute("error", "用户未登录");
            return "publish";
        }
        Question question = new Question();
        question.setId(id);
        question.setTitle(title);
        question.setDescription(description);
        question.setTag(tag);
        question.setCreator(user.getId());
        /*questionService.createQuestion(question);*/
        questionService.createOrUpdate(question);
        return "redirect:/";
    }

}
