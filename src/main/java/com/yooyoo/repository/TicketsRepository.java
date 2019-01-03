package com.yooyoo.repository;

import java.util.Set;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.yooyoo.model.Ticket;

@Repository
public interface TicketsRepository extends CrudRepository<Ticket, Integer>{
	
	@Query("select s from Ticket AS s")
	public Set<Ticket> getAllTickets();


}
