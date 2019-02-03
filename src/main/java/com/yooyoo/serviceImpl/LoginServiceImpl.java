package com.yooyoo.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

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
import com.yooyoo.util.VOMapper;
import com.yooyoo.vo.LoginVO;
import com.yooyoo.vo.Profile;
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
		String mobileNo = loginVO.getMobileNo();
		String password = loginVO.getPassword();
		List<Student> students = studentRepo.getStudentDetailsBySchoolMobileAndPassword(school.getId(), password,
				mobileNo);
		if (students != null && !students.isEmpty()) {
			String sessionId = UUID.randomUUID().toString();
			profile.setSchool(school);
			List<StudentVO> studentsVos = new ArrayList<>();
			for (Student student : students) {
				SessionManager manager = new SessionManager();
				manager.setSessionId(sessionId);
				manager.setUserId(student.getId());
				manager.setSource("mobile");
				sessionRepo.save(manager);
				StudentVO vo = new StudentVO();
				vo = VOMapper.getStudent(student);
				studentsVos.add(vo);
			}
			profile.setAccessToken(sessionId);
			profile.setStudents(studentsVos);
			profile.setStatus(200);
			profile.setMessage("User Loggedin Sucessfully...");
		} else {
			profile.setMessage("User not found with given credentials");
			profile.setStatus(404);
		}

		return profile;
	}

}

