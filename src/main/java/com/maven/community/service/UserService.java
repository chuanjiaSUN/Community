package com.maven.community.service;


import com.maven.community.pojo.User;
import com.maven.community.pojo.UserExample;

import java.util.List;

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

    /**
     * findByToken 通过token查询
     * @param token github的token信息
     * @return user
     * */
    List<User> findByToken(String token);

    /**
     * findById
     * @param creator github用户的id
     * @return user
     * */
    User findById(Long creator);


    /**
     * createOrUpdate
     * @param storeUser 需要存储的用户 或 更新的用户
     * */
    void createOrUpdate(User storeUser);

    /**
     * selectByExample APi方法
     * @param example 对象
     * @return list
     * */
    List<User> selectByExample(UserExample example);
}
