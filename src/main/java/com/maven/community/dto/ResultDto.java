package com.maven.community.dto;

import com.maven.community.exception.CustomizeErrorCode;
import com.maven.community.exception.CustomizeException;
import lombok.Data;


/**
 * @author sunchuanjia
 * @Description
 * @create 2021-08-21 16:17
 */
@Data
public class ResultDto<T> {
    private Integer code;
    private String message;
    private T data;

    public static ResultDto errorOf(Integer code, String message)
    {
        ResultDto resultDto = new ResultDto();
        resultDto.setCode(code);
        resultDto.setMessage(message);
        return resultDto;
    }

    public static ResultDto errorOf(CustomizeErrorCode errorCode) {
        ResultDto resultDto = new ResultDto();
        resultDto.setCode(errorCode.getCode());
        resultDto.setMessage(errorCode.getMessage());
        return resultDto;
    }

    public static ResultDto okOf()
    {
        ResultDto resultDto = new ResultDto();
        resultDto.setCode(200);
        resultDto.setMessage("请求成功!!!");
        return resultDto;
    }

    public static ResultDto errorOf(CustomizeException ex) {
        return errorOf(ex.getCode(), ex.getMessage());
    }

    public static <T> ResultDto okOf(T t)
    {
        ResultDto resultDto = new ResultDto();
        resultDto.setCode(200);
        resultDto.setMessage("请求成功");
        resultDto.setData(t);
        return resultDto;
    }
}
