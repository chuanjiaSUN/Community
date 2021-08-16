package com.maven.community.mapper;

import com.maven.community.pojo.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
 * @author sunchuanjia
 * @Description
 * @create 2021-08-15 18:57
 */
@Mapper
@Repository("UserMapper")
public interface UserMapper {

    /**
     * insert 向数据库插入 user信息
     * @param user 授权登录并成功的用户
     * */
    @Insert("insert into user (name,account_id,token,gmt_create,gmt_modified,avatar_url) values(#{name}, #{accountId}, " +
            "#{token}, #{gmtCreate}, #{gmtModified}, #{avatarUrl})")
    void insert(User user);


    /**
     * find 登录时通过token在数据库查询是否已存在user信息
     * @param token 字符串token值
     * @return user 从数据库查询到的用户
     * */
    @Select("select * from user where token=#{token}")
    User find(String token);

    /**
     * findById 通过id查询user
     * @param id github用户的id
     * @return user
     * */
    @Select("select * from user where id=#{id}")
    User findById(Integer id);
}
