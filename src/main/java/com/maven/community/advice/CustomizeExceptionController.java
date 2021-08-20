package com.maven.community.advice;

import com.maven.community.exception.CustomizeException;
import org.springframework.boot.autoconfigure.web.servlet.error.BasicErrorController;
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * @author sunchuanjia
 * @Description
 * @create 2021-08-21 0:39
 */
@ControllerAdvice
public class CustomizeExceptionController {

    @ExceptionHandler(Exception.class)
    ModelAndView handle(HttpServletRequest request, Throwable ex, Model model)
    {
        if (ex instanceof CustomizeException)
        {
            model.addAttribute("message", ex.getMessage());
        }else{
            model.addAttribute("message", "服务器冒烟了，要不然稍后再试试!!!");
        }
        return new ModelAndView("error");
    }
}
