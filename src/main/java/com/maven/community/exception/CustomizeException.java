package com.maven.community.exception;

/**
 * @author sunchuanjia
 * @Description
 * @create 2021-08-21 1:14
 */
public class CustomizeException extends RuntimeException{
    private String message = null;

    public CustomizeException(ICustomizeErrorCode errorCode)
    {
        this.message = errorCode.getMessage();
    }

    public CustomizeException(String message)
    {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
