package com.yooyoo.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yooyoo.model.School;
import com.yooyoo.model.Ticket;
import com.yooyoo.repository.TicketsRepository;
import com.yooyoo.service.SchoolService;
import com.yooyoo.service.TicketsServise;
import com.yooyoo.util.VOMapper;
import com.yooyoo.vo.TicketVO;

@Service
public class TicketServiceImpl implements TicketsServise{
	
	@Autowired
	TicketsRepository repository;
	
	@Autowired
	SchoolService schoolServise;

	@Override
	public void saveTicket(TicketVO ticket) {
		School school = schoolServise.getSchoolById(ticket.getSchoolId());
		if (school != null) {
			Ticket schoolTicket = VOMapper.getTicket(ticket);
			schoolTicket.setSchoolId(school);
			repository.save(schoolTicket);
		}

	}

	@Override
	public List<TicketVO> getAllTickets() {
		List<TicketVO> tickets = new ArrayList<>();
		Set<Ticket> schoolTkts = repository.getAllTickets();
		for(Ticket tkt : schoolTkts){
			TicketVO vo = new TicketVO();
			vo = VOMapper.getTicket(tkt);
			tickets.add(vo);
		}
		return tickets;
	}

	@Override
	public List<TicketVO> getAllTicketBySchool() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteTickets(Integer id) {
		if(id != null){
			repository.deleteById(id);
		}
		
	}

}
