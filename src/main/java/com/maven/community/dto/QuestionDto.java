package com.maven.community.dto;

import com.maven.community.pojo.Question;
import com.maven.community.pojo.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author sunchuanjia
 * @Description
 * @create 2021-08-16 16:29
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuestionDto {
    private Question question;
    private User user;
}
