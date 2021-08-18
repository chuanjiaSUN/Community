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

    @Override
    public User findById(Integer creator) {
        return userMapper.findById(creator);
    }

    @Override
    public void createOrUpdate(User user) {
        User dbUser = userMapper.findByAccountId(user.getAccountId());
        if (dbUser == null)
        {
            user.setGmtCreate(System.currentTimeMillis());
            user.setGmtModified(user.getGmtCreate());
            storeUser(user);
        }else{
            //若有该user,则更新
            user.setGmtCreate(System.currentTimeMillis());
            user.setGmtModified(user.getGmtCreate());
            user.setAvatarUrl(user.getAvatarUrl());
            user.setName(user.getName());
            user.setToken(user.getToken());
            userMapper.updateUser(user);
        }
    }
}
