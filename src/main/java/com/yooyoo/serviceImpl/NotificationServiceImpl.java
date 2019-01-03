package com.yooyoo.serviceImpl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yooyoo.model.Grade;
import com.yooyoo.model.Notifications;
import com.yooyoo.model.School;
import com.yooyoo.model.Student;
import com.yooyoo.repository.GradeRepository;
import com.yooyoo.repository.NotificationRepository;
import com.yooyoo.repository.SchoolRepository;
import com.yooyoo.repository.StudentRepository;
import com.yooyoo.service.NotificationService;
import com.yooyoo.util.VOMapper;
import com.yooyoo.vo.NotificationsVO;


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

	@Override
	public void saveNotification(NotificationsVO notificationVO) {
		//Long schoolId = Long.valueOf(notificationVO.getSchoolId());
		Optional<School> school = schoolRepository.findById(notificationVO.getSchoolId());
		Student student = studentRepository.findById(notificationVO.getStudentId());
		Grade grade = gradeRepository.findById(notificationVO.getGradeId());
		Notifications notification = VOMapper.getNotificationModel(notificationVO);
		notification.setSchool(school.get());
		notification.setGrade(grade);
		notification.setStudent(student);
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

}
