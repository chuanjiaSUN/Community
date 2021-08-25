package com.maven.community.dto;

import lombok.Data;

/**
 * @author sunchuanjia
 * @Description
 * @create 2021-08-25 16:36
 */
@Data
public class NotificationDto {
    private Long id;
    private Long gmtCreate;
    private Integer status;
    private Long outerid;
    private Long notifier;
    private String notifierName;
    private String outerTitle;
    private String typeName;
    private Integer type;
}
