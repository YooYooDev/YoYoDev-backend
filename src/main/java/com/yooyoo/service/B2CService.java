package com.yooyoo.service;

import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import com.yooyoo.model.B2CCustomer;
import com.yooyoo.vo.ResultVO;

public interface B2CService {

	ResultVO register(B2CCustomer b2cUser);

	List<B2CCustomer> getB2CCustomersByMobile(String mobileNo, String otp);

	List<B2CCustomer> getB2CCustomersByMobile(String mobileNo);
	
	void updateB2CProfile(B2CCustomer b2cUser);

	ResultVO deleteB2Cuser(B2CCustomer b2cUser);
	
	ResultVO sendEmailForIntrested(Integer userId)throws AddressException, MessagingException;


}
