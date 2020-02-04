package com.yooyoo.serviceImpl;

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.yooyoo.model.B2CCustomer;
import com.yooyoo.model.Grade;
import com.yooyoo.model.School;
import com.yooyoo.model.Student;
import com.yooyoo.repository.B2CCustomerRepository;
import com.yooyoo.repository.GradeRepository;
import com.yooyoo.repository.SchoolRepository;
import com.yooyoo.repository.StudentRepository;
import com.yooyoo.service.B2CService;
import com.yooyoo.service.LoginService;
import com.yooyoo.util.EmailUtil;
import com.yooyoo.util.VOMapper;
import com.yooyoo.vo.ResultVO;

@Service
public class B2CServiceImpl implements B2CService{
	
	public static final String B2C = "B2C";
	
	@Autowired
	private B2CCustomerRepository b2Crepo;
	
	@Autowired
	private StudentRepository studentRepo;
	
	@Autowired
	private SchoolRepository schoolRepo;
	
	@Autowired
	private GradeRepository gradeRepo;
	
	@Autowired
	private LoginService loginService;
	
	@Value("${school.id}")
	private Integer schoolId;

	@Override
	public ResultVO register(B2CCustomer b2cUser) {
		ResultVO result = new ResultVO();
		if (b2cUser != null) {
			String name = b2cUser.getName();
			String email = b2cUser.getEmail();
			String mobile = b2cUser.getMobile();
			String address = b2cUser.getAddress();
			String birthdate = b2cUser.getBirthdate();
			String gender = b2cUser.getGender();
			B2CCustomer b2c = new B2CCustomer();
			Optional<School> school = schoolRepo.findById(schoolId);
			Grade uGrade = gradeRepo.getGradebyName("ALL");
			if (checkForDuplicate(b2cUser)) {
				if (school.isPresent() && uGrade != null) {
					Student student = new Student();
					student.setAddress(address);
					student.setCity(address);
					student.setGrade(uGrade);
					student.setSchool(school.get());
					student.setType(B2C);
					student.setFirst_name(name);
					student.setP_email(email);
					student.setS_email(email);
					student.setParent_mobile1(mobile);
					student.setParent_mobile2(mobile);
					try {
						if (b2cUser.getBirthdate() != null) {
							student.setDob(VOMapper.simpleDateFormat.parse(b2cUser.getBirthdate()));
						}
					} catch (ParseException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					student.setGender(gender);
					b2c.setId(b2cUser.getId());
					b2c.setBirthdate(birthdate);
					b2c.setGender(gender);
					b2c.setStudent(student);
					b2c.setSchool(school.get());
					b2c.setName(name);
					b2c.setEmail(email);
					b2c.setGrade("ALL");
					b2c.setMobile(mobile);
					b2c.setAddress(address);
					try {
						studentRepo.save(student);
						b2Crepo.save(b2c);
						b2c.setOtp(loginService.sendOtpForB2CForReg(mobile,b2c.getEmail()));
						result.setStatus(200);
						result.setMessage("User has been registered Sucessfully");
					} catch (MalformedURLException | UnsupportedEncodingException e) {
						result.setStatus(400);
						result.setMessage("failed to register user");
					}

				}

			} else {
				result.setStatus(400);
				result.setMessage("User already exists in System, Please login with your mobile no.");

			}

		}
		return result;
	}

	private boolean checkForDuplicate(B2CCustomer b2cUser) {
		String email = b2cUser.getEmail();
		String mobile = b2cUser.getMobile();
		List<B2CCustomer> custmers = new ArrayList<>();
		custmers = b2Crepo.getCustomersByMobileEmail(mobile, email);
		return custmers.isEmpty();

	}

	@Override
	public List<B2CCustomer> getB2CCustomersByMobile(String mobileNo, String otp) {
		List<B2CCustomer> custmers = new ArrayList<>();
		custmers = b2Crepo.getCustomersByMobile(mobileNo, otp);
		return custmers;
	}
	
	@Override
	public List<B2CCustomer> getB2CCustomersByMobile(String mobileNo) {
		List<B2CCustomer> custmers = new ArrayList<>();
		custmers = b2Crepo.getCustomersByMobile(mobileNo);
		return custmers;
	}

	@Override
	public void updateB2CProfile(B2CCustomer b2cUser) {
		if (b2cUser != null) {
			Optional<B2CCustomer> b2c = b2Crepo.findById(b2cUser.getId());
			if (b2c.isPresent()) {
				B2CCustomer b2cDB = b2c.get();
				String name = b2cUser.getName();
				String email = b2cUser.getEmail();
				String mobile = b2cUser.getMobile();
				String address = b2cUser.getAddress();
				String grade = b2cUser.getGrade();
				Student student = b2cDB.getStudent();
				String birthdate = b2cUser.getBirthdate();
				String gender = b2cUser.getGender();
				Grade sGrade = gradeRepo.getGradebyName(grade);
				if (address != null) {
					student.setAddress(address);
					student.setCity(address);
				}
				if (sGrade != null) {
					student.setGrade(sGrade);
				}
				if (name != null) {
					b2cDB.setName(name);
					student.setFirst_name(name);
				}
				if (email != null) {
					b2cDB.setEmail(email);
					student.setP_email(email);
					student.setS_email(email);
				}
				if (mobile != null) {
					b2cDB.setMobile(mobile);
					student.setParent_mobile1(mobile);
					student.setParent_mobile2(mobile);
				}
				if (birthdate != null) {
					try {
						b2cDB.setBirthdate(birthdate);
						student.setDob(VOMapper.simpleDateFormat.parse(birthdate));
					} catch (ParseException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				if (gender != null) {
					b2cDB.setGender(gender);
					student.setGender(gender);
				}

				b2cDB.setStudent(student);
				b2cDB.setGrade(grade);
				studentRepo.save(student);
				b2Crepo.save(b2cDB);
			}
		}
		
	}

	@Override
	public ResultVO deleteB2Cuser(B2CCustomer b2cUser) {
		ResultVO result = new ResultVO();
		if (b2cUser.getMobile() != null) {
			List<B2CCustomer> b2cUsers = b2Crepo.getCustomersByMobile(b2cUser.getMobile());
			for (B2CCustomer cust : b2cUsers) {
				Student s = cust.getStudent();
				studentRepo.delete(s);
				b2Crepo.delete(cust);
			}
			result.setStatus(200);
			result.setMessage("Record has been deleted sucessfully...");

		}else{
			result.setStatus(400);
			result.setMessage("Error in deleting user..");
		}
		
		return result;
	}

	@Override
	public ResultVO sendEmailForIntrested(Integer b2cId) throws AddressException, MessagingException {
		ResultVO result = new ResultVO();
		if (b2cId != null) {
			Optional<B2CCustomer> b2c = b2Crepo.findById(b2cId);
			if (b2c.isPresent()) {
				EmailUtil.sendB2cIntrestedemail(b2c.get());
				result.setStatus(200);
				result.setMessage("Email sent sucessfully...");
				
			}}
		return result;
	}

}
