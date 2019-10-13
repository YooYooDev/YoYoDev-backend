package com.yooyoo.serviceImpl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.validation.constraints.NotNull;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yooyoo.model.CredManager;
import com.yooyoo.model.School;
import com.yooyoo.model.SessionManager;
import com.yooyoo.model.Student;
import com.yooyoo.repository.CredManagerRepository;
import com.yooyoo.repository.SchoolRepository;
import com.yooyoo.repository.SessionRepository;
import com.yooyoo.repository.StudentRepository;
import com.yooyoo.service.LoginService;
import com.yooyoo.util.EmailUtil;
import com.yooyoo.util.VOMapper;
import com.yooyoo.vo.LoginVO;
import com.yooyoo.vo.Profile;
import com.yooyoo.vo.ResultVO;
import com.yooyoo.vo.StudentVO;

@Service
public class LoginServiceImpl implements LoginService {
	Logger logger = LoggerFactory.getLogger(LoginServiceImpl.class);
	@Autowired
	CredManagerRepository credRepo;
	
	@Autowired
	SchoolRepository schoolRepo;
	
	@Autowired
	StudentRepository studentRepo;
	
	@Autowired
	SessionRepository sessionRepo;

	@Override
	public CredManager authenticate(CredManager credManager) {
		CredManager manager = credRepo.getUserDetails(credManager.getEmail(), credManager.getPassword());
		logger.info("Inside loginservice impl");
		return manager;
	}

	@Override
	@NotNull
	public Profile authenticateMobileUser(LoginVO loginVO) {
		Profile profile = new Profile();
		Integer schoolCode = Integer.valueOf(loginVO.getSchoolCode());
		School school = schoolRepo.findByCode(schoolCode);
		String email = loginVO.getEmail();
		String password = loginVO.getPassword();
		if(school != null){
		List<Student> students = studentRepo.getStudentDetailsBySchoolMobileAndPassword(school.getId(), password,
				email);
			if (students != null && !students.isEmpty()) {
				String sessionId = UUID.randomUUID().toString();
				profile.setSchool(school);
				List<StudentVO> studentsVos = new ArrayList<>();
				for (Student student : students) {
					SessionManager sessionManager = sessionRepo.findByUserId(student.getId());
					if (sessionManager != null) {

						profile.setAccessToken(sessionManager.getSessionId());
					} else {
						SessionManager manager = new SessionManager();
						manager.setSessionId(sessionId);
						manager.setUserId(student.getId());
						manager.setSource("mobile");
						sessionRepo.save(manager);
						profile.setAccessToken(sessionId);
					}
					StudentVO vo = new StudentVO();
					vo = VOMapper.getStudent(student);
					studentsVos.add(vo);
				}
				profile.setStudent(studentsVos.get(0));
				profile.setStatus(200);
				profile.setMessage("User Loggedin Sucessfully...");
			} else {
			profile.setMessage("User not found with given credentials");
			profile.setStatus(404);
		}
		}else{
			profile.setMessage("School not found with given credentials");
			profile.setStatus(404);
		}

		return profile;
	}

	private boolean doesSessionExistsForuser(int id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ResultVO sendForgotPasswordEmail(LoginVO logInVO) {
		ResultVO result = new ResultVO();
		if(logInVO.getEmail() != null){
			List<Student> s = studentRepo.getStudentDetailsByEmail(logInVO.getEmail());
			if(!s.isEmpty()){
				Student student  = s.get(0);
				String pemail = student.getP_email();
				String semail = student.getS_email();
				try {
					EmailUtil.sendmail(pemail, student.getPassword());
					EmailUtil.sendmail(semail, student.getPassword());
					result.setStatus(200);
					result.setMessage("Password has been sent to your registered emailid.");
				} catch (AddressException e) {
					result.setStatus(500);
					result.setMessage("Unable to send Email. ");
					e.printStackTrace();
				} catch (MessagingException e) {
					result.setStatus(500);
					result.setMessage("Unable to send Email. ");
					e.printStackTrace();
				} catch (IOException e) {
					result.setStatus(500);
					result.setMessage("Unable to send Email. ");
					e.printStackTrace();
				}
			}else{
				result.setStatus(500);
				result.setMessage("User not found for  Given email. ");
			}
		}
		return result;
	}

}

