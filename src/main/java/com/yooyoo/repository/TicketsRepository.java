package com.yooyoo.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.yooyoo.model.Ticket;

@Repository
public interface TicketsRepository extends CrudRepository<Ticket, Integer>{

}
