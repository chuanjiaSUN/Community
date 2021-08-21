package com.maven.community.service.serviceImpl;

import com.maven.community.mapper.UserMapper;
import com.maven.community.pojo.User;
import com.maven.community.pojo.UserExample;
import com.maven.community.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public List<User> findByToken(String token) {
        UserExample userExample = new UserExample();
        userExample.createCriteria()
                .andTokenEqualTo(token);
        List<User> users = userMapper.selectByExample(userExample);
        return users;
    }

    @Override
    public User findById(Long creator) {
        UserExample userExample = new UserExample();
        userExample.createCriteria().andIdEqualTo(creator);
        List<User> users = userMapper.selectByExample(userExample);
        if (users.size() != 0)
        {
            return users.get(0);
        }else{
            return null;
        }
    }

    @Override
    public void createOrUpdate(User user) {
        UserExample userExample = new UserExample();
        userExample.createCriteria().andAccountIdEqualTo(user.getAccountId());
        List<User> users = userMapper.selectByExample(userExample);
        User dbUser;
        if (users.size() != 0)
        {
            dbUser = users.get(0);
        }else{
            dbUser = null;
        }
        if (dbUser == null)
        {
            user.setGmtCreate(System.currentTimeMillis());
            user.setGmtModified(user.getGmtCreate());
            storeUser(user);
        }else{
            //若有该user,则更新
            User updateUser = new User();
            updateUser.setToken(user.getToken());
            updateUser.setName(user.getName());
            updateUser.setGmtModified(System.currentTimeMillis());
            updateUser.setAvatarUrl(user.getAvatarUrl());
            UserExample example = new UserExample();
            example.createCriteria().andIdEqualTo(dbUser.getId());
            userMapper.updateByExampleSelective(updateUser, userExample);
        }
    }
}
