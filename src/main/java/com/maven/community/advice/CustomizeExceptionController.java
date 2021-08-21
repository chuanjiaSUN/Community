package com.maven.community.advice;

import com.alibaba.fastjson.JSON;
import com.maven.community.dto.ResultDto;
import com.maven.community.exception.CustomizeErrorCode;
import com.maven.community.exception.CustomizeException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author sunchuanjia
 * @Description
 * @create 2021-08-21 0:39
 */
@ControllerAdvice
public class CustomizeExceptionController {

    private static final String APPLICATION_REQUEST_TYPE_JSON = "application/json";
    @ExceptionHandler(Exception.class)
    Object handle(HttpServletRequest request, Throwable ex, Model model,
                  HttpServletResponse response)
    {
        String contentType = request.getContentType();
        if(APPLICATION_REQUEST_TYPE_JSON.equals(contentType))
        {
            ResultDto resultDto = null;
            //返回json
            if (ex instanceof CustomizeException)
            {
                resultDto =  ResultDto.errorOf((CustomizeException)ex);
            }else{
                ex.printStackTrace();
                resultDto = ResultDto.errorOf(CustomizeErrorCode.SYS_ERROR);
            }

            PrintWriter writer = null;
            try {
                response.setContentType("application/json");
                response.setStatus(200);
                response.setCharacterEncoding("UTF-8");
                writer = response.getWriter();
                writer.write(JSON.toJSONString(resultDto));
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }else{
            //错误页面跳转
            if (ex instanceof CustomizeException)
            {
                model.addAttribute("message", ex.getMessage());
            }else{
                ex.printStackTrace();
                return ResultDto.errorOf(CustomizeErrorCode.SYS_ERROR);
            }
            return new ModelAndView("error");
        }
    }
}
