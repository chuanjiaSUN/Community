package com.maven.community.enums;


/**
 * @author sunchuanjia
 * @Description
 * @create 2021-08-21 16:20
 */
public enum CommentTypeEnum {
    /**
     * 问题
     * */
    QUESTION(1),
    /*
    * 评论
    * */
    COMMENT(2);

    private int type;
    CommentTypeEnum(Integer type)
    {
        this.type = type;
    }

    public static boolean isExist(Integer type) {
        for (CommentTypeEnum commentTypeEnum : CommentTypeEnum.values())
        {
            if (commentTypeEnum.getType() == type){
                return true;
            }
        }
        return false;
    }

    public int getType() {
        return type;
    }
}
