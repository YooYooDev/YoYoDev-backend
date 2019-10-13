package com.yooyoo.service;

import java.util.List;

import com.yooyoo.vo.FeedBackVO;
import com.yooyoo.vo.NotificationsVO;
import com.yooyoo.vo.ResultVO;

public interface NotificationService {

	public void saveNotification(NotificationsVO notificationVO);

	public List<NotificationsVO> getAllNotifications();

	public List<NotificationsVO> getAllNotificationsBySchool();
	
	public List<NotificationsVO> getNotificationsBySchoolANdClass(int id);

	public void deleteNotificaitons(Integer id, boolean delete);
	
	public List<NotificationsVO> getNotificationByUser(int studentId);
	
	public ResultVO updateNotification(int notificationId);
	
	public ResultVO saveFeedBack(FeedBackVO feedback);

}
