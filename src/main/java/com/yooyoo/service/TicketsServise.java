package com.yooyoo.service;

import java.util.List;

import com.yooyoo.vo.NotificationsVO;

public interface TicketsServise {
	
	public void saveTicket(NotificationsVO notificationVO);

	public List<NotificationsVO> getAllTickets();

	public List<NotificationsVO> getAllTicketBySchool();

	public void deleteTickets(Integer id);

}
