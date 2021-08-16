package com.maven.community.pojo;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * @author sunchuanjia
 * @Description
 * @create 2021-08-15 22:27
 */
@Data
@Setter
@Getter
public class Question {
    private Integer id;
    private String title;
    private String description;
    private Long gmtCreate;
    private Long gmtModified;
    private Integer creator;
    private Integer commentCount;
    private Integer viewCount;
    private Integer likeCount;
    private String tag;
}
