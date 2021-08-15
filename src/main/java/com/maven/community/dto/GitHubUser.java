package com.maven.community.dto;


import lombok.Data;
import lombok.Getter;
import lombok.ToString;

/**
 * @author sunchuanjia
 * @Description
 * @create 2021-08-14 21:35
 */
@Data
@Getter
@ToString
public class GitHubUser {
    private String name;
    private Long id;
    private String bio;
    private String avatarUrl;
}
