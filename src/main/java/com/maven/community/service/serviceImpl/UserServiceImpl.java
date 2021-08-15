package com.maven.community.service.serviceImpl;

import com.maven.community.mapper.UserMapper;
import com.maven.community.pojo.User;
import com.maven.community.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author sunchuanjia
 * @Description
 * @create 2021-08-15 19:08
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public void storeUser(User user) {
        userMapper.insert(user);
    }

    @Override
    public User findByToken(String token) {
       return userMapper.find(token);
    }
}
