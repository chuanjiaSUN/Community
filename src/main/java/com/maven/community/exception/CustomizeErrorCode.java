package com.maven.community.exception;

/**
 * @author sunchuanjia
 * @Description
 * @create 2021-08-21 1:22
 */
public enum CustomizeErrorCode implements ICustomizeErrorCode{

    /**
     * not found
     * */
    QUESTION_NOT_FOUND("你找的问题不在了,要不换个试试?");

    private String message = null;
    CustomizeErrorCode(String message)
    {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
