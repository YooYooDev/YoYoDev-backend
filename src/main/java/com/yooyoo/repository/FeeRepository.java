package com.yooyoo.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.yooyoo.model.Fees;

@Repository
public interface FeeRepository extends CrudRepository<Fees, Integer> {

}
