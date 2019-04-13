package com.yooyoo.serviceImpl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yooyoo.model.FeedBack;
import com.yooyoo.model.Grade;
import com.yooyoo.model.Notifications;
import com.yooyoo.model.School;
import com.yooyoo.model.Student;
import com.yooyoo.repository.FeedBackRepository;
import com.yooyoo.repository.GradeRepository;
import com.yooyoo.repository.NotificationRepository;
import com.yooyoo.repository.SchoolRepository;
import com.yooyoo.repository.StudentRepository;
import com.yooyoo.service.NotificationService;
import com.yooyoo.util.VOMapper;
import com.yooyoo.vo.FeedBackVO;
import com.yooyoo.vo.NotificationsVO;
import com.yooyoo.vo.ResultVO;


@Service
public class NotificationServiceImpl implements NotificationService{
	
	@Autowired
	NotificationRepository repository;
	@Autowired
	SchoolRepository schoolRepository;
	@Autowired
	GradeRepository gradeRepository;
    @Autowired
	StudentRepository studentRepository;
    
    @Autowired
    FeedBackRepository feedbackRepository;
    

	@Override
	public void saveNotification(NotificationsVO notificationVO) {
		Optional<School> school = schoolRepository.findById(notificationVO.getSchoolId());
		Student student = studentRepository.findById(notificationVO.getStudentId());
		Grade grade = gradeRepository.findById(notificationVO.getGradeId());
		Notifications notification = VOMapper.getNotificationModel(notificationVO);
		notification.setSchool(school.get());
		notification.setGrade(grade);
		notification.setStudent(student);
		notification.setDeleted("N");
		repository.save(notification);
		
	}

	@Override
	public List<NotificationsVO> getAllNotifications() {
		List<NotificationsVO> notifiList = new ArrayList<>();
		List<Notifications> notis = repository.findAll();
		for(Notifications not : notis){
			notifiList.add(VOMapper.getNotificationVO(not));
		}
	
		return notifiList;
	}

	@Override
	public List<NotificationsVO> getAllNotificationsBySchool() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteNotificaitons(Integer id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<NotificationsVO> getNotificationsBySchoolANdClass(NotificationsVO notificationVO) {
		List<NotificationsVO> notifiList = new ArrayList<>();
		Set<Notifications> notis = new HashSet();
		int schoolId = notificationVO.getSchoolId();
		//int gradeId = notificationVO.getGradeId();
		//int studentId = notificationVO.getStudentId();
		//default notifications
		notis = repository.getNotificationDetailsBySchoolIdAndStudentId(1,1,1);
		if(schoolId !=0){
			Set<Notifications> schoolNotis = repository.getNotificationDetailsBySchoolId(schoolId);
			notis.addAll(schoolNotis);
		}
		for(Notifications not : notis){
			notifiList.add(VOMapper.getNotificationVO(not));
		}
		return notifiList;
	}

	@Override
	public List<NotificationsVO> getNotificationByUser(int studentId) {
		List<NotificationsVO> notifiList = new ArrayList<>();
		List<Notifications> notis = null;
		if(studentId !=0){
			notis = repository.getNotificationDetailsUserId(studentId);
		}
		for(Notifications not : notis){
			notifiList.add(VOMapper.getNotificationVO(not));
		}
		return notifiList;
	}

	@Override
	public ResultVO updateNotification(int notificationId) {
		ResultVO resultVO = new ResultVO();
		Optional<Notifications> notice = repository.findById(notificationId);
		if (notice != null) {
			Notifications assignment = notice.get();
			assignment.setDeleted("Y");
			repository.save(assignment);
			resultVO.setStatus(200);
			resultVO.setMessage("Notification removed successfully...");
		} else {
			resultVO.setStatus(404);
			resultVO.setMessage("Notification not found for given Id");
		}
		return resultVO;
	}

	@Override
	public ResultVO saveFeedBack(FeedBackVO feedback) {
		ResultVO vo = new ResultVO();
		if(feedback != null && feedback.getStudentId() != 0){
			FeedBack fBack = new FeedBack();
			Student student =  studentRepository.findById(feedback.getStudentId());
			fBack.setStudent(student);
			fBack.setMessage(feedback.getMessage());
			feedbackRepository.save(fBack);
			vo.setStatus(200);
			vo.setMessage("Feedback saved sucessfully...");
			
		}else{
			vo.setStatus(400);
			vo.setMessage("Issue with saving feedback...");
		}
		return vo;
	}

}
