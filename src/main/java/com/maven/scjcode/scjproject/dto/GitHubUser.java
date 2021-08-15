package com.maven.scjcode.scjproject.dto;


import lombok.Data;

/**
 * @author sunchuanjia
 * @Description
 * @create 2021-08-14 21:35
 */
@Data
public class GitHubUser {
    private String name;
    private Long id;
    private String bio;
    private String avatarUrl;
}
