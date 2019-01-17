/**
 * 
 */
package com.yooyoo.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.yooyoo.model.Role;

/**
 * @author RLE0305
 *
 */
@Repository
public interface RoleRepository extends CrudRepository<Role, Integer>{

}
