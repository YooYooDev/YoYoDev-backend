package com.yooyoo.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.yooyoo.model.Attendance;

@Repository
public interface AttendanceRepository extends CrudRepository<Attendance, Integer>{

}
