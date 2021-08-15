package com.maven.community.mapper;

import com.maven.community.pojo.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
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
    @Insert("insert into user (name,account_id,token,gmt_create,gmt_modified) values(#{name}, #{accountId}, " +
            "#{token}, #{gmtCreate}, #{gmtModified})")
    void insert(User user);


}
