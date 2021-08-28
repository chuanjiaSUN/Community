package com.maven.community.dto;

import lombok.Data;

/**
 * @author sunchuanjia
 * @Description
 * @create 2021-08-27 23:06
 */
@Data
public class QuestionQueryDto {
    private String search;
    private Integer page;
    private Integer size;
}
