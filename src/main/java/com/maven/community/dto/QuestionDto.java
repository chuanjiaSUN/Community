package com.maven.community.dto;

import com.maven.community.pojo.Question;
import com.maven.community.pojo.User;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author sunchuanjia
 * @Description
 * @create 2021-08-16 16:29
 */
@Data
@AllArgsConstructor
public class QuestionDto {
    private Question question;
    private User user;
}
