package com.yooyoo.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.yooyoo.model.Assignment;
import com.yooyoo.model.CredManager;
import com.yooyoo.model.Fees;
import com.yooyoo.model.Grade;
import com.yooyoo.model.Notifications;
import com.yooyoo.model.Question;
import com.yooyoo.model.Quiz;
import com.yooyoo.model.School;
import com.yooyoo.model.Student;
import com.yooyoo.model.Ticket;
import com.yooyoo.model.Topic;
import com.yooyoo.vimeo.pojo.Vimeo;
import com.yooyoo.vo.CredManagerVO;
import com.yooyoo.vo.FeesVO;
import com.yooyoo.vo.GradeVO;
import com.yooyoo.vo.MobileAssignmentVO;
import com.yooyoo.vo.NotificationsVO;
import com.yooyoo.vo.QuestionVO;
import com.yooyoo.vo.QuizVO;
import com.yooyoo.vo.SchoolInfo;
import com.yooyoo.vo.StudentFeeVO;
import com.yooyoo.vo.StudentVO;
import com.yooyoo.vo.TicketVO;
import com.yooyoo.vo.VideoVO;

public class VOMapper {

	static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");

	public static Student getStudent(StudentVO studentVO) {
		Student student = new Student();
		student.setId(studentVO.getId());
		student.setFirst_name(studentVO.getFirstName());
		student.setMiddle_name(studentVO.getMiddleName());
		student.setLast_name(studentVO.getLastName());
		student.setRole("STUDENT");
		// student.setSchool(getSchool(studentVO.getSchool()));
		student.setP_email(studentVO.getPrimaryEmail());
		student.setS_email(studentVO.getSecondaryEmail());
		student.setParent_mobile1(studentVO.getParentMobile1());
		student.setParent_mobile2(studentVO.getParentMobile2());
		if (studentVO.getPassword() != null) {
			student.setPassword(studentVO.getPassword());
		} else {
			student.setPassword("password");
		}
		int deleted = studentVO.getDeleted();
		if (deleted == 1) {
			student.setDeleted("Y");
		} else if (deleted == 0) {
			student.setDeleted("N");
		}
		// new fields
		try {
			student.setDob(simpleDateFormat.parse(studentVO.getDob()));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		student.setPhoto(studentVO.getPhoto());
		student.setGender(studentVO.getGender());
		student.setFatherName(studentVO.getFatherName());
		student.setMotherName(studentVO.getMotherName());
		student.setFatherProfession(studentVO.getFatherProfession());
		student.setMotherProfession(studentVO.getMotherProfession());
		student.setAddress(studentVO.getAddress());
		student.setCity(studentVO.getCity());
		student.setPinCode(studentVO.getPinCode());
		student.setState(studentVO.getState());
		return student;
	}

	public static School getSchool(SchoolInfo schoolVO) {
		School school = new School();
		school.setId(schoolVO.getId());
		school.setName(schoolVO.getName());
		school.setCode(schoolVO.getCode());
		school.setAddress(schoolVO.getAddress());
		school.setPost(schoolVO.getPost());
		school.setPin(schoolVO.getPin());
		school.setState(schoolVO.getState());
		school.setCountry(schoolVO.getCountry());
		school.setContactPerson(schoolVO.getContactPerson());
		school.setRegistrationName(schoolVO.getRegistrationName());
		school.setEmailId(schoolVO.getEmailId());
		school.setOwnerMobile(schoolVO.getOwnerMobile());
		school.setOwnerName(schoolVO.getOwnerName());
		school.setEnableAttendance(schoolVO.getEnableAttendance());
		school.setEnableFees(schoolVO.getEnableFees());
		school.setEnablePrintedWorksheet(schoolVO.getEnablePrintedWorksheet());
		return school;
	}

	public static StudentVO getStudent(Student student) {
		StudentVO studentVO = new StudentVO();
		studentVO.setId(student.getId());
		studentVO.setFirstName(student.getFirst_name());
		studentVO.setLastName(student.getLast_name());
		studentVO.setMiddleName(student.getMiddle_name());
		studentVO.setRole("STUDENT");
		studentVO.setPassword(student.getPassword());
		studentVO.setPrimaryEmail(student.getP_email());
		studentVO.setSecondaryEmail(student.getS_email());
		studentVO.setParentMobile1(student.getParent_mobile1());
		studentVO.setParentMobile2(student.getParent_mobile2());
		String deleted = student.getDeleted();
		if (deleted.equalsIgnoreCase("N")) {
			studentVO.setDeleted(0);
		} else if (deleted.equalsIgnoreCase("Y")) {
			studentVO.setDeleted(1);
		}

		// new fields
		// new fields
		studentVO.setDob(student.getDob() != null ? student.getDob().toString().substring(0, 10) : "date");
		studentVO.setPhoto(student.getPhoto());
		studentVO.setGender(student.getGender());
		studentVO.setFatherName(student.getFatherName());
		studentVO.setMotherName(student.getMotherName());
		studentVO.setFatherProfession(student.getFatherProfession());
		studentVO.setMotherProfession(student.getMotherProfession());
		studentVO.setAddress(student.getAddress());
		studentVO.setCity(student.getCity());
		studentVO.setPinCode(student.getPinCode());
		studentVO.setState(student.getState());
		Grade grade = student.getGrade();
		School school = student.getSchool();
		studentVO.setGradeId(grade != null ? grade.getId() : 0);
		studentVO.setGradeName(grade != null ? grade.getName() : "Grade");
		studentVO.setSchoolId(school != null ? school.getId() : 0);
		return studentVO;
	}

	public static Notifications getNotificationModel(NotificationsVO notificationVO) {
		Notifications noti = new Notifications();
		noti.setId(notificationVO.getId());
		noti.setHeader(notificationVO.getHeader());
		noti.setMessage(notificationVO.getMessage());
		noti.setCreated_at(new Date());
		noti.setUpdatedd_at(new Date());
		return noti;
	}

	public static NotificationsVO getNotificationVO(Notifications not) {
		NotificationsVO notVo = new NotificationsVO();
		notVo.setId(not.getId());
		notVo.setHeader(not.getHeader());
		notVo.setMessage(not.getMessage());
		notVo.setSchoolId(not.getSchool() != null ? not.getSchool().getId() : 0);
		notVo.setGradeId(not.getGrade() != null ? not.getGrade().getId() : 0);
		notVo.setStudentId(not.getStudentId() != null ? not.getStudentId() : 0);
		notVo.setSchoolName(not.getSchool()!= null?not.getSchool().getName():"SCHOOL");
		notVo.setGradeName(not.getGrade() != null?not.getGrade().getName():"ALL");
		notVo.setCreated_at(not.getCreated_at());
		return notVo;
	}

	public static List<GradeVO> formatStudentsPerGrade(List<Student> students) {
		List<Grade> grades = students.stream().map(student -> student.getGrade()).collect(Collectors.toList());
		Set<Grade> gradess = new HashSet<>(grades);
		List<GradeVO> gradeVOs = new ArrayList<>();
		for (Grade grade : gradess) {
			GradeVO vo = new GradeVO();
			List<StudentVO> studentsVos = new ArrayList<>();
			List<Student> students1 = students.stream().filter(
					line -> line.getGrade() != null && line.getGrade().getName().equalsIgnoreCase(grade.getName()))
					.collect(Collectors.toList());
			for (Student student : students1) {
				StudentVO vo1 = new StudentVO();
				vo1 = VOMapper.getStudent(student);
				studentsVos.add(vo1);
			}
			vo.setStudents(studentsVos);
			vo.setId(grade.getId());
			vo.setName(grade.getName());
			gradeVOs.add(vo);
		}
		return gradeVOs;
	}

	public static Ticket getTicket(TicketVO ticket) {
		Ticket schoolTicket = new Ticket();
		schoolTicket.setId(ticket.getId());
		schoolTicket.setSubject(ticket.getSubject());
		schoolTicket.setMessage(ticket.getMessage());
		schoolTicket.setResolution(ticket.getResolution());
		schoolTicket.setDeleted("N");
		return schoolTicket;
	}

	public static TicketVO getTicket(Ticket tkt) {
		TicketVO vo = new TicketVO();
		vo.setId(tkt.getId());
		vo.setSubject(tkt.getSubject());
		vo.setMessage(tkt.getMessage());
		vo.setResolution(tkt.getResolution());
		vo.setSchoolId(tkt.getSchoolId() != null ? tkt.getSchoolId().getId() : 0);
		return vo;
	}

	public static CredManager getCredManagerModel(CredManagerVO credVo) {
		CredManager manager = new CredManager();
		manager.setId(credVo.getId());
		manager.setFullName(credVo.getFullName());
		manager.setUser_name(credVo.getUserName());
		manager.setEmail(credVo.getEmail());
		manager.setMobile(credVo.getMobile());
		manager.setPassword(credVo.getPassword());
		manager.setCity(credVo.getCity());
		manager.setId(credVo.getId());
		return manager;
	}

	public static CredManagerVO getCredManagerVO(CredManager credVo) {
		CredManagerVO manager = new CredManagerVO();
		manager.setFullName(credVo.getFullName());
		manager.setUserName(credVo.getUser_name());
		manager.setEmail(credVo.getEmail());
		manager.setMobile(credVo.getMobile());
		manager.setPassword(credVo.getPassword());
		manager.setCity(credVo.getCity());
		manager.setId(credVo.getId());
		manager.setRole(credVo.getRole().getName());
		School school = credVo.getSchool();
		manager.setSchoolId(school != null ? credVo.getSchool().getId() : 0);
		manager.setSchoolName(school != null ? credVo.getSchool().getName() : null);
		return manager;
	}

	public static Fees getFeesModel(FeesVO fees) {
		Fees fee = new Fees();
		fee.setStudentName(fees.getStudentName());
		fee.setCreated_at(new Date());
		fee.setUpdatedd_at(new Date());
		fee.setTransportationFee(fees.getTransportationFee());
		fee.setTutionFee(fees.getTutionFee());
		fee.setPaidTransportFee(fees.getPaidTransportFee());
		fee.setPaidTutionFee(fees.getPaidTutionFee());
		fee.setId(fees.getId());
		return fee;
	}

	public static FeesVO getFeesVO(Fees fees) {
		FeesVO fee = new FeesVO();
		fee.setStudentName(fees.getStudentName());
		fee.setCreated_at(new Date());
		fee.setUpdatedd_at(new Date());
		fee.setTransportationFee(fees.getTransportationFee());
		fee.setTutionFee(fees.getTutionFee());
		fee.setPaidTransportFee(fees.getPaidTransportFee());
		fee.setPaidTutionFee(fees.getPaidTutionFee());
		fee.setId(fees.getId());
		fee.setSchoolId(fees.getSchool() != null ? fees.getSchool().getId() : 0);
		fee.setStudentId(fees.getStudent() != null ? fees.getStudent().getId() : 0);
		Grade grade = fees.getStudent() != null ? fees.getStudent().getGrade() : null;
		if (grade != null) {
			fee.setGradeName(grade != null ? grade.getName() : null);
		}

		if ("Y".equalsIgnoreCase(fees.getDeleted())) {
			fee.setDeleted(0);
		} else if (("N".equalsIgnoreCase(fees.getDeleted()))) {
			fee.setDeleted(1);
		}
		System.out.println(fees.getImage1());
		byte[] image = fee.getImage1();
		fee.setImage1(image);
		return fee;
	}
	/*
	 * public static Assignment getAssignment(AssignmentVO assignmentVO) {
	 * Assignment assignment = new Assignment(); return null; }
	 */

	public static MobileAssignmentVO getAssignmentVO(Assignment assignment) {
		MobileAssignmentVO mobileAssignment = new MobileAssignmentVO();
		mobileAssignment.setAssignmentId(assignment.getId());
		Topic topic = assignment.getTopic();
		if (topic != null) {
			mobileAssignment.setWorkSheetLink(topic.getWorksheetLink());
			mobileAssignment.setTopicId(topic.getId());
		}
		DateFormat dateFormat = new SimpleDateFormat("dd-MMM-yy");
		String strDate = dateFormat.format(assignment.getDate());
		mobileAssignment.setDate(strDate);
		mobileAssignment.setDescription(assignment.getSubject().getName() + " : " + assignment.getTopic().getName());

		return mobileAssignment;
	}

	public static Quiz getQuizModel(QuizVO quiz) {
		Quiz quizModel = new Quiz();
		List<Question> questionsModel = new ArrayList<>();
		if (quiz != null) {
			quizModel.setId(quiz.getId());
			quizModel.setTopic_id(quiz.getTopicId());
			quizModel.setQuiz_name(quiz.getQuizName());
			List<QuestionVO> questions = quiz.getQuestions();
			for (QuestionVO q : questions) {
				Question question = new Question();
				question.setId(q.getId());
				question.setQuestion(q.getQuestion());
				question.setAnswer(q.getAnswer());
				question.setOption1(q.getOption1());
				question.setOption2(q.getOption2());
				questionsModel.add(question);
			}
			quizModel.setQuestions(questionsModel);
		}
		return quizModel;
	}

	public static QuizVO getQuizVO(Quiz q) {
		QuizVO vo = new QuizVO();
		List<QuestionVO> qs = new ArrayList<>();
		vo.setId(q.getId());
		vo.setQuizName(q.getQuiz_name());
		vo.setTopicId(q.getTopic_id());
		List<Question> ques = q.getQuestions();
		for (Question qu : ques) {
			QuestionVO qvo = new QuestionVO();
			qvo.setId(qu.getId());
			qvo.setQuestion(qu.getQuestion());
			qvo.setAnswer(qu.getAnswer());
			qvo.setOption1(qu.getOption1());
			qvo.setOption2(qu.getOption2());
			qs.add(qvo);
		}
		vo.setQuestions(qs);
		return vo;
	}

	public static VideoVO getVideoVO(Topic topic) {
		VideoVO vo = new VideoVO();
		if (topic != null) {
			APIClient apiClient = new APIClient();
			Vimeo vimeo = apiClient.getVideoLink(topic.getVideoLink());
			vo.setVideoLink(vimeo.getVideoLlink());
			vo.setVideoThumbnail(vimeo.getThumbLInk());
			vo.setVideoSubtitle(vimeo.getSubTitle());
		}
		return vo;

	}

	public static StudentFeeVO getStudentFeeVO(StudentVO student) {
		StudentFeeVO studentVO = new StudentFeeVO();
		studentVO.setId(student.getId());
		studentVO.setFirstName(student.getFirstName());
		studentVO.setLastName(student.getLastName());
		studentVO.setMiddleName(student.getMiddleName());
		studentVO.setGradeId(student.getGradeId());
		studentVO.setGradeName(student.getGradeName());
		studentVO.setSchoolId(student.getSchoolId());
		return studentVO;
	}

}
