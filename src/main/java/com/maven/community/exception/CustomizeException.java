package com.maven.community.exception;

/**
 * @author sunchuanjia
 * @Description
 * @create 2021-08-21 1:14
 */
public class CustomizeException extends RuntimeException{
    private String message = null;
    private Integer code;

    public CustomizeException(ICustomizeErrorCode errorCode)
    {
        this.code = errorCode.getCode();
        this.message = errorCode.getMessage();
    }

    @Override
    public String getMessage() {
        return message;
    }

    public Integer getCode() {
        return code;
    }
}
