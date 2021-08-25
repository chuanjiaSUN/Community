package com.maven.community.enums;

/**
 * @author sunchuanjia
 * @Description
 * @create 2021-08-25 16:17
 */
public enum NotificationStatusEnum {
    /**
     * 未读、已读
     * */
    UNREAD(0),READ(1)
    ;
    private int status;

    public int getStatus() {
        return status;
    }

    NotificationStatusEnum(int status) {
        this.status = status;
    }
}
