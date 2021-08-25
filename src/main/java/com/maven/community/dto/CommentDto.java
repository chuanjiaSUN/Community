package com.maven.community.dto;

import com.maven.community.pojo.User;
import lombok.Data;


/**
 * @author sunchuanjia
 * @Description
 * @create 2021-08-23 16:03
 */
@Data
public class CommentDto {
    private Long id;
    private Long parentId;
    private Integer type;
    private Long commentator;
    private Long gmtCreat;
    private Long gmtModified;
    private Long likeCount;
    private String content;
    private Integer commentCount;
    private User user;
}
