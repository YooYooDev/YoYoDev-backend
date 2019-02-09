package com.yooyoo.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.yooyoo.model.Student;
import com.yooyoo.model.Subject;

@Repository
public interface SubjectRepository extends CrudRepository<Subject, Integer>{

}
