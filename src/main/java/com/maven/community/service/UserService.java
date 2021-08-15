package com.maven.community.service;


import com.maven.community.pojo.User;

/**
 * @author sunchuanjia
 * @Description
 * @create 2021-08-15 19:06
 */
public interface UserService {
    /**
     * storeUser 存储user信息的Service层
     * @param user 用户信息
     * */
    void storeUser(User user);
}
