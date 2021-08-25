package com.maven.community.service.serviceImpl;

import com.maven.community.dto.NotificationDto;
import com.maven.community.dto.PaginationDto;
import com.maven.community.enums.NotificationStatusEnum;
import com.maven.community.enums.NotificationTypeEnum;
import com.maven.community.exception.CustomizeErrorCode;
import com.maven.community.exception.CustomizeException;
import com.maven.community.mapper.NotificationMapper;
import com.maven.community.pojo.*;
import com.maven.community.service.NotificationService;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


/**
 * @author sunchuanjia
 * @Description
 * @create 2021-08-25 16:03
 */
@Service
public class NotificationServiceImpl implements NotificationService {

    @Autowired
    private NotificationMapper notificationMapper;

    @Override
    public void insert(Notification notification) {
        notificationMapper.insert(notification);
    }


    @Override
    public PaginationDto<NotificationDto> listUserNotifications(Long id, Integer page, Integer size) {
        PaginationDto<NotificationDto> paginationDto = new PaginationDto<>();

        NotificationExample notificationExample = new NotificationExample();
        notificationExample.createCriteria().andReceiverEqualTo(id);
        List<Notification> notifications = notificationMapper.selectByExample(notificationExample);
        paginationDto.setPagination(notifications.size(), page, size);
        if (page < 1)
        {
            page = 1;
        }
        if (page > paginationDto.getTotalPages())
        {
            page = paginationDto.getTotalPages();
        }
        Integer offSet = size * (page - 1);
        /*List<Question> questionList = questionMapper.listPageById(id, offSet, size);*/
        NotificationExample example = new NotificationExample();
        example.setOrderByClause("gmt_create desc");
        example.createCriteria().andReceiverEqualTo(id);
        List<Notification> notificationPage = notificationMapper.selectByExampleWithBLOBsWithRowbounds(example, new RowBounds(offSet, size));
        if (notificationPage.size() == 0)
        {
            return paginationDto;
        }
        //获取通知的人
        List<NotificationDto> notificationDtos = new ArrayList<>();
        for (Notification notification : notificationPage)
        {
            NotificationDto dto = new NotificationDto();
            BeanUtils.copyProperties(notification, dto);
            dto.setTypeName(NotificationTypeEnum.nameOfType(notification.getType()));
            notificationDtos.add(dto);
        }

        paginationDto.setData(notificationDtos);
        return paginationDto;
    }

    @Override
    public Long unreadCount(Long userId) {
        NotificationExample example = new NotificationExample();
        example.createCriteria()
                .andReceiverEqualTo(userId)
                .andStatusEqualTo(NotificationStatusEnum.UNREAD.getStatus());
       return notificationMapper.countByExample(example);
    }

    @Override
    public NotificationDto read(Long id, User user) {
        Notification notification = notificationMapper.selectByPrimaryKey(id);
        if (notification == null)
        {
            throw new CustomizeException(CustomizeErrorCode.NOTIFICATION_NOT_FOUND);
        }
        if (!notification.getReceiver().equals(user.getId()))
        {
            throw new CustomizeException(CustomizeErrorCode.READ_NOTIFICATION_FAIL);
        }
        //设置为已读
        notification.setStatus(NotificationStatusEnum.READ.getStatus());
        notificationMapper.updateByPrimaryKey(notification);

        NotificationDto notificationDto = new NotificationDto();
        BeanUtils.copyProperties(notification, notificationDto);
        notificationDto.setTypeName(NotificationTypeEnum.nameOfType(notification.getType()));
        return notificationDto;
    }
}
