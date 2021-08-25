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
    QUESTION_NOT_FOUND(2001,"你找的问题不在了,要不换个试试?"),
    TARGET_PARAM_NOT_FOUND(2002,"未选择任何问题进行回复"),
    NO_LOGIN(2003, "未登录不能评论， 请先登录"),
    SYS_ERROR(2004, "服务器冒烟了，要不然稍后再试试!!!"),
    TYPE_PARAM_WRONG(2005, "评论类型错误或不存在!!!"),
    COMMENT_NOT_FOUND(2006, "你操作的评论不存在了，要不换个试试?"),
    CONTENT_IS_EMPTY(2007, "您提交的评论为空"),
    READ_NOTIFICATION_FAIL(2008, "你是读别人的信息吗?"),
    NOTIFICATION_NOT_FOUND(2009, "消息莫非不在了?");

    private String message = null;
    private Integer code;
    CustomizeErrorCode(Integer code, String message)
    {
        this.code = code;
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public Integer getCode() {
        return code;
    }

}
