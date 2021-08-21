package com.maven.community.dto;

import lombok.Data;

/**
 * @author sunchuanjia
 * @Description
 * @create 2021-08-21 15:44
 */
@Data
public class CommentDto {
    private Long parentId;
    private String content;
    private Integer type;
}
