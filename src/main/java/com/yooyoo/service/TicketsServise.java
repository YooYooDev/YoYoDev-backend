package com.yooyoo.service;

import java.util.List;

import com.yooyoo.vo.TicketVO;

public interface TicketsServise {
	
	public void saveTicket(TicketVO ticket);

	public List<TicketVO> getAllTickets();

	public List<TicketVO> getAllTicketBySchool();

	public void deleteTickets(Integer id);

}
