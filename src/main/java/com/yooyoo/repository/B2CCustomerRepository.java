package com.yooyoo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.yooyoo.model.B2CCustomer;

@Repository
public interface B2CCustomerRepository extends CrudRepository<B2CCustomer, Integer>{
	
	public static String GET_CUSTOMER_BY_MOBILE = "select c from B2CCustomer c where c.mobile = :mobile ";
	
	public static String GET_CUSTOMER_BY_MOBILE_EMAIL = "select c from B2CCustomer c where c.mobile = :mobile and c.email = :email";
	
	public static String GET_CUSTOMER_BY_OTP_MOBILE = "select c from B2CCustomer c where c.mobile = :mobile and c.otp = :otp";
	
	@Query(GET_CUSTOMER_BY_MOBILE)
	public List<B2CCustomer> getCustomersByMobile(@Param("mobile") String mobile);
	
	@Query(GET_CUSTOMER_BY_MOBILE_EMAIL)
	public List<B2CCustomer> getCustomersByMobileEmail(@Param("mobile") String mobile, @Param("email") String email);
	
	@Query(GET_CUSTOMER_BY_OTP_MOBILE)
	public List<B2CCustomer> getCustomersByMobile(@Param("mobile") String mobile, @Param("otp") String otp);

}
