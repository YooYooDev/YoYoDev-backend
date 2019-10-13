package com.yooyoo.serviceImpl;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.yooyoo.model.Assignment;
import com.yooyoo.model.Grade;
import com.yooyoo.model.QuestionMedia;
import com.yooyoo.model.Result;
import com.yooyoo.model.School;
import com.yooyoo.model.SessionManager;
import com.yooyoo.model.Subject;
import com.yooyoo.model.Topic;
import com.yooyoo.model.Tracker;
import com.yooyoo.repository.AssignmentRepository;
import com.yooyoo.repository.GradeRepository;
import com.yooyoo.repository.QuestionMediaRepository;
import com.yooyoo.repository.ResultRepository;
import com.yooyoo.repository.SessionRepository;
import com.yooyoo.repository.TrackerRepository;
import com.yooyoo.service.AssignmentService;
import com.yooyoo.service.CurriculumService;
import com.yooyoo.service.QuizService;
import com.yooyoo.service.SchoolService;
import com.yooyoo.util.VOMapper;
import com.yooyoo.vo.AssignmentVO;
import com.yooyoo.vo.CurriculamVO;
import com.yooyoo.vo.MobileAssignmentVO;
import com.yooyoo.vo.QuestionVO;
import com.yooyoo.vo.QuizVO;
import com.yooyoo.vo.ResultVO;
import com.yooyoo.vo.VideoVO;

@Service
public class AssignmentServiceImpl implements AssignmentService {

	Logger logger = LoggerFactory.getLogger(AssignmentServiceImpl.class);
	@Autowired
	AssignmentRepository assignmentRepository;

	@Autowired
	CurriculumService curriculamService;

	@Autowired
	SchoolService schoolService;

	@Autowired
	GradeRepository gradeRepo;

	@Autowired
	QuestionMediaRepository questionMediaRepo;

	@Autowired
	QuizService quizService;

	@Autowired
	SessionRepository sessionRepository;

	@Autowired
	ResultRepository resultRepository;
	
	@Autowired
	TrackerRepository trackerrepository;

	@Value("${media.url}")
	private String mediaUrl;

	@Override
	public ResultVO createAssignment(AssignmentVO assignmentVO) throws ParseException {
		ResultVO result = new ResultVO();
		DateFormat formatter;
		Date date = null;
		Date toDate = null;
		if (assignmentVO != null) {
			if (assignmentVO.getDate() == null && assignmentVO.getSchoolId() == null) {
				Assignment ass = assignmentRepository.findById(assignmentVO.getId()).get();
				formatter = new SimpleDateFormat("dd-MMM-yy");
				Date dateUpdate = formatter.parse(assignmentVO.getToDate());
				ass.setToDate(dateUpdate);
				assignmentRepository.save(ass);
				result.setStatus(200);
				result.setMessage("Assignment updated Sucessfully...");

			} else {
				try {
					Topic topic = curriculamService.getTopicById(assignmentVO.getTopicId());
					Subject subject = curriculamService.getSubjectById(assignmentVO.getSubjectId());
					School school = schoolService.getSchoolById(assignmentVO.getSchoolId());
					if (assignmentVO.getGrade().equalsIgnoreCase("all")) {
						Iterable<Grade> itr = gradeRepo.findAll();
						for (Grade g : itr) {
							Assignment assignment = new Assignment();
							assignment.setGrade(g);
							assignment.setTopic(topic);
							assignment.setSubject(subject);
							assignment.setSchool(school);
							formatter = new SimpleDateFormat("dd-MMM-yy");
							date = formatter.parse(assignmentVO.getDate());
							assignment.setDate(date);
							if (assignmentVO.getToDate() != null) {
								toDate = formatter.parse(assignmentVO.getToDate());
								assignment.setToDate(toDate);
							}
							assignmentRepository.save(assignment);
						}
					} else {
						Grade grade = gradeRepo.getGradebyName(assignmentVO.getGrade());
						Assignment assignment = null;
						if (assignmentVO.getId() != null) {
							Optional<Assignment> optAssignment = assignmentRepository.findById(assignmentVO.getId());
							if (optAssignment.isPresent()) {
								assignment = optAssignment.get();
							} else {
								assignment = new Assignment();
							}
						} else {
							assignment = new Assignment();
						}
						assignment.setGrade(grade);
						assignment.setTopic(topic);
						assignment.setSubject(subject);
						assignment.setSchool(school);
						formatter = new SimpleDateFormat("dd-MMM-yy");
						date = formatter.parse(assignmentVO.getDate());
						assignment.setDate(date);
						if (assignmentVO.getToDate() != null) {
							toDate = formatter.parse(assignmentVO.getToDate());
							assignment.setToDate(toDate);
						}
						assignmentRepository.save(assignment);
					}

					result.setStatus(200);
					result.setMessage("Assignment Saved Sucessfully...");
				} catch (DataIntegrityViolationException e) {
					result.setStatus(400);
					result.setMessage("Duplicate Record.....");
				} catch (Exception e) {
					result.setStatus(400);
					result.setMessage("Unable to save Assignment...");
				}
			}

		}
		return result;
	}

	@Override
	public ResultVO updateAssignment(AssignmentVO assignmentVO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResultVO deleteAssignMent(Integer id) {
		ResultVO result = new ResultVO();
		if (id != 0) {
			Optional<Assignment> assignment = assignmentRepository.findById(id);
			assignmentRepository.delete(assignment.get());
			result.setStatus(200);
			result.setMessage("Assignment deleted sucessfully...");
		} else {
			result.setStatus(400);
			result.setMessage("Unable to delete Assignment...");
		}
		return result;
	}

	@Override
	public List<Assignment> getAllAssignment() {
		List<Assignment> assignments = new ArrayList<>();
		Iterable<Assignment> assigns = assignmentRepository.findAll();
		for (Assignment a : assigns) {
			a.getTopic().setWorkSheetImage(null);
			a.getTopic().setThumbnailImage(null);
			assignments.add(a);
		}
		return assignments;
	}

	@Override
	public List<Assignment> getAssignmentBySchool(Integer id) {
		List<Assignment> assignments = new ArrayList<>();
		Iterable<Assignment> assigns = assignmentRepository.getAssignmentsBySchool(id);
		for (Assignment a : assigns) {
			a.getTopic().setWorkSheetImage(null);
			assignments.add(a);
		}
		return assignments;
	}

	public List<Assignment> getAssignmentBySchoolAndMonth(Integer id, String month) {
		List<Assignment> assignments = new ArrayList<>();
		Iterable<Assignment> assigns = assignmentRepository.getAssignmentsBySchoolAndMonth(id, month);
		for (Assignment a : assigns) {
			assignments.add(a);
		}
		return assignments;
	}

	@Override
	public List<Topic> getTopicsBySubject(Integer id) {
		return curriculamService.getTopicBySubjectId(id);
	}

	@Override
	public List<MobileAssignmentVO> getAssignmentBySchoolAndGrade(Integer schoolId, String grade, String token) {
		List<Assignment> assignments = null;
		List<MobileAssignmentVO> assignMentVos = new ArrayList<>();
		Grade gradeEntiry = gradeRepo.getGradebyName(grade);
		SessionManager session = sessionRepository.getSessionByToken(token);
		try {
			assignments = assignmentRepository.getAssignmentsBySchoolAndGrade(schoolId, gradeEntiry.getId());
			for (Assignment assignment : assignments) {
				MobileAssignmentVO assignmentVO = VOMapper.getAssignmentVO(assignment);
				assignmentVO.setAssignmentId(assignment.getId());
				assignmentVO.setWorkSheetMedia(mediaUrl + "getworksheetlink/" + assignmentVO.getTopicId() + "/?t="
						+ System.currentTimeMillis());
				assignmentVO
						.setWorkSheetAppeared(hasAppeared(session, assignment.getId(), TrackerServiceImpl.WORKSHEET));
				assignmentVO.setSubject(assignment.getTopic().getSubjects().getName());
				assignmentVO.setCurriculam(false);
				assignMentVos.add(assignmentVO);
				
			}
		} catch (Exception e) {
			logger.error("internal seerver error" + e);
		}
		return assignMentVos;
	}

	// for curricullam section
	@Override
	public List<MobileAssignmentVO> getAssignmentByAssignmentId(Integer assignmentId) {
		Assignment assignment = null;
		List<MobileAssignmentVO> assignMentVos = new ArrayList<>();
		try {
			assignment = assignmentRepository.findById(assignmentId).get();
			if (assignment != null) {
				MobileAssignmentVO assignmentVO = VOMapper.getAssignmentVO(assignment);
				assignmentVO.setCurriculam(true);
				assignmentVO.setAssignmentId(assignment.getId());
				assignmentVO.setSubject(assignment.getTopic().getSubjects().getName());
				assignmentVO.setWorkSheetMedia(mediaUrl + "getworksheetlink/" + assignmentVO.getTopicId() + "/?t="
						+ System.currentTimeMillis());
				assignMentVos.add(assignmentVO);
			} else {
				logger.info("getAssignmentdetails by id returned empty...");
			}
		} catch (Exception e) {
			logger.error("internal seerver error" + e);
		}
		return assignMentVos;
	}

	@Override
	public List<CurriculamVO> getCurriculamsForSchool(int schoolId, String month) {
		List<CurriculamVO> curries = new ArrayList<>();
		List<Assignment> assignments = getAssignmentBySchoolAndMonth(schoolId, month);
		if (assignments != null && !assignments.isEmpty()) {
			for (Assignment ass : assignments) {
				CurriculamVO cur = new CurriculamVO();
				cur.setAssignmentId(ass.getId());
				cur.setSubject(ass.getTopic().getSubjects().getName());
				cur.setTopicName(ass.getTopic().getName());
				cur.setSubjectMedia(
						mediaUrl + "getsubjectmedia/" + ass.getSubject().getId() + "/?t=" + System.currentTimeMillis());
				DateFormat dateFormat = new SimpleDateFormat("dd-MMM-yy");
				String strDate = dateFormat.format(ass.getDate());
				cur.setDate(strDate);
				cur.setCurriculam(true);
				curries.add(cur);
			}
		}
		return curries;
	}

	@Override
	public MobileAssignmentVO finaAssignmentById(int assignMentid) {
		List<VideoVO> videos = new ArrayList<>();
		List<QuestionVO> questions = new ArrayList<>();
		MobileAssignmentVO assignmentVO = null;
		Optional<Assignment> assignment = assignmentRepository.findById(assignMentid);
		if (assignment != null) {
			assignmentVO = VOMapper.getAssignmentVO(assignment.get());
			assignmentVO.setCurriculam(true);
			assignmentVO.setSubject(assignment.get().getTopic().getSubjects().getName());
			assignmentVO.setAssignmentId(assignment.get().getId());
			VideoVO vo = VOMapper.getVideoVO(assignment.get().getTopic());
			videos.add(vo);
			assignmentVO.setVideos(videos);
			List<QuizVO> quizs = quizService.getQuizByTopic(assignment.get().getTopic().getId());
			if (!quizs.isEmpty()) {
				List<QuestionVO> q = quizs.get(0).getQuestions();
				questions.addAll(q);
				for (QuestionVO qs : questions) {
					QuestionMedia med = questionMediaRepo.findByQuestionId(qs.getId());
					if (med != null) {
						qs.setContentType(med.getContentType());
						qs.setMediaLink(mediaUrl + "getMedia/" + qs.getId() + "/?t=" + System.currentTimeMillis());
					}
				}
				assignmentVO.setQuestions(questions);
			}
		}
		return assignmentVO;
	}

	@Override
	public List<VideoVO> getAssignmentVideosBySchoolAndGrade(Integer schoolId, String grade, String token) {
		List<Assignment> assignments = null;
		List<VideoVO> videos = new ArrayList<>();
		Grade gradeEntiry = gradeRepo.getGradebyName(grade);
		SessionManager session = sessionRepository.getSessionByToken(token);
		try {
			assignments = assignmentRepository.getAssignmentsBySchoolAndGrade(schoolId, gradeEntiry.getId());
			for (Assignment assignment : assignments) {
				VideoVO vo = VOMapper.getVideoVO(assignment.getTopic());
				byte[] vidMed = curriculamService.getWorkThumbNailMedia(assignment.getTopic().getId());
				if (vidMed != null) {
					vo.setVideoThumbnail(mediaUrl + "getthumbnaillink/" + assignment.getTopic().getId() + "/?t="
							+ System.currentTimeMillis());
				}
				vo.setHasViewed(hasAppeared(session, assignment.getId(), TrackerServiceImpl.VIDEO));
				vo.setSubject(assignment.getTopic().getSubjects().getName());
				vo.setAssignmentId(assignment.getId());
				vo.setCurriculam(false);
				videos.add(vo);
			}
		} catch (Exception e) {
			logger.error("internal seerver error" + e);
		}
		return videos;
	}

	// videos for curricullam
	@Override
	public List<VideoVO> getVideosByAssignmentId(Integer assignmentId) {
		Assignment assignment = null;
		List<VideoVO> videos = new ArrayList<>();
		try {
			assignment = assignmentRepository.findById(assignmentId).get();
			if (assignment != null) {
				VideoVO vo = VOMapper.getVideoVO(assignment.getTopic());
				byte[] vidMed = curriculamService.getWorkThumbNailMedia(assignment.getTopic().getId());
				if (vidMed != null) {
					vo.setVideoThumbnail(mediaUrl + "getthumbnaillink/" + assignment.getTopic().getId() + "/?t="
							+ System.currentTimeMillis());
				}
				vo.setCurriculam(true);
				vo.setSubject(assignment.getTopic().getSubjects().getName());
				vo.setAssignmentId(assignment.getId());
				videos.add(vo);
			} else {
				logger.info("No videos found for given assignment");
			}
		} catch (Exception e) {
			logger.error("internal seerver error" + e);
		}
		return videos;
	}

	@Override
	public List<QuestionVO> getAssignmentQuestionsBySchoolAndGrade(Integer schoolId, String grade, String token) {
		List<Assignment> assignments = null;
		List<QuestionVO> questions = new ArrayList<>();
		SessionManager session = sessionRepository.getSessionByToken(token);
		Grade gradeEntiry = gradeRepo.getGradebyName(grade);
		try {
			assignments = assignmentRepository.getAssignmentsBySchoolAndGrade(schoolId, gradeEntiry.getId());
			for (Assignment assignment : assignments) {
				List<QuizVO> quizs = quizService.getQuizByTopic(assignment.getTopic().getId());
				if (!quizs.isEmpty()) {
					List<QuestionVO> q = quizs.get(0).getQuestions();
					questions.addAll(q);
					for (QuestionVO qs : questions) {
						qs.setAssignmentId(assignment.getId());
						qs.setAppeared(isAppeared(session, qs));
						qs.setCurriculam(false);
						QuestionMedia med = questionMediaRepo.findByQuestionId(qs.getId());
						if (med != null) {
							qs.setContentType(med.getContentType());
							qs.setMediaLink(mediaUrl + "getMedia/" + qs.getId() + "/?t=" + System.currentTimeMillis());
						}
					}
				}
			}
		} catch (Exception e) {
			logger.error("internal seerver error" + e);
		}
		return questions;
	}

	// questions for curriculam
	@Override
	public List<QuestionVO> getQuestionsByAssignmentId(Integer assignmentId, String token) {
		Assignment assignment = null;
		SessionManager session = sessionRepository.getSessionByToken(token);
		List<QuestionVO> questions = new ArrayList<>();
		try {
			assignment = assignmentRepository.findById(assignmentId).get();
			if (assignment != null) {
				List<QuizVO> quizs = quizService.getQuizByTopic(assignment.getTopic().getId());
				if (!quizs.isEmpty()) {
					List<QuestionVO> q = quizs.get(0).getQuestions();
					questions.addAll(q);
					for (QuestionVO qs : questions) {
						qs.setAppeared(isAppeared(session, qs));
						qs.setCurriculam(true);
						QuestionMedia med = questionMediaRepo.findByQuestionId(qs.getId());
						if (med != null) {
							qs.setContentType(med.getContentType());
							qs.setMediaLink(mediaUrl + "getMedia/" + qs.getId() + "/?t=" + System.currentTimeMillis());
						}
					}
				}
			} else {
				logger.info("quiz not found for given assignment");
			}
		} catch (Exception e) {
			logger.error("internal seerver error" + e);
		}
		return questions;
	}

	private boolean isAppeared(SessionManager session, QuestionVO qs) {
		List<Result> results = resultRepository.getResultByStudentId(session.getUserId());
		return results.stream().anyMatch(rs -> (rs.getQuestionId() == qs.getId()));
	}

	@Override
	public List<QuizVO> getQuizByTopic(Integer topicId) {
		List<QuestionVO> questions = new ArrayList<>();
		List<QuizVO> quizs = quizService.getQuizByTopic(topicId);
		if (!quizs.isEmpty()) {
			List<QuestionVO> q = quizs.get(0).getQuestions();
			questions.addAll(q);
			for (QuestionVO qs : questions) {
				QuestionMedia med = questionMediaRepo.findByQuestionId(qs.getId());
				if (med != null) {
					qs.setContentType(med.getContentType());
					qs.setMediaLink(mediaUrl + "getMedia/" + qs.getId() + "/?t=" + System.currentTimeMillis());
				}
			}
		}
		return quizs;
	}

	private boolean hasAppeared(SessionManager session, Integer id, String type) {
		boolean hasAppeared = false;
        List<Tracker> t = trackerrepository.getTrackerModelForStudent(session.getUserId(), id, type);
        if(t != null && t.size()>0){
        	hasAppeared = true;
        }
		return hasAppeared;
	}

}
