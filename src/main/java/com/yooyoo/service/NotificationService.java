package com.yooyoo.service;

import java.util.List;

import com.yooyoo.vo.NotificationsVO;

public interface NotificationService {

	public void saveNotification(NotificationsVO notificationVO);

	public List<NotificationsVO> getAllNotifications();

	public List<NotificationsVO> getAllNotificationsBySchool();
	
	public List<NotificationsVO> getNotificationsBySchoolANdClass(NotificationsVO notificationVO);

	public void deleteNotificaitons(Integer id);

}
