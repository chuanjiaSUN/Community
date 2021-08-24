package com.maven.community.dto;

import lombok.Data;

import java.util.List;

/**
 * @author sunchuanjia
 * @Description
 * @create 2021-08-24 21:24
 */
@Data
public class TagDto {
    private String categoryName;
    private List<String> tags;
}
