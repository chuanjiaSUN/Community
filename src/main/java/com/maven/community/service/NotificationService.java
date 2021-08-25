package com.maven.community.service;

import com.maven.community.dto.NotificationDto;
import com.maven.community.dto.PaginationDto;
import com.maven.community.pojo.Notification;
import com.maven.community.pojo.User;

/**
 * @author sunchuanjia
 * @Description
 * @create 2021-08-25 16:03
 */
public interface NotificationService {
    /**
     * 插入评论通知
     * @param notification 通知
     * */
    void insert(Notification notification);

    /**
     * 查询通知的分页
     * @param id 用户id
     * @param page 页数
     * @param size 页大小
     * @return paginationDto 分页对象
     * */
    PaginationDto<NotificationDto> listUserNotifications(Long id, Integer page, Integer size);

    /**
     * 获取未读通知书
     * @param id 用户id
     * @return long
     * */
    Long unreadCount(Long id);

    /**
     * 查询通知
     * @param id 用户id
     * @param user 用户
     * @return NotificationDto
     * */
    NotificationDto read(Long id, User user);
}
