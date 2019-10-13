package com.yooyoo.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yooyoo.model.Tracker;
import com.yooyoo.repository.TrackerRepository;
import com.yooyoo.service.TrackerService;
import com.yooyoo.vo.ResultVO;
import com.yooyoo.vo.TrackerVO;

@Service
public class TrackerServiceImpl implements TrackerService{
	
	@Autowired
	TrackerRepository trackerRepository;
	
	public static final String VIDEO = "video";
	
	public static final String WORKSHEET = "worksheet";

	@Override
	public ResultVO saveTrackedRecord(TrackerVO tracker) {
		ResultVO result = new ResultVO();
		if (tracker != null) {
			try {
				List<Tracker> trackerL = trackerRepository.getTrackerModelForStudent(tracker.getStudentId(),
						tracker.getAssignmentId(), tracker.getTrackingType());
				if (trackerL == null || trackerL.isEmpty()) {
					Tracker tr = new Tracker();
					tr.setAssignmentId(tracker.getAssignmentId());
					tr.setStudentId(tracker.getStudentId());
					tr.setSubject(tracker.getSubject());
					tr.setTrackingType(tracker.getTrackingType());
					trackerRepository.save(tr);
				}
				result.setStatus(200);
				result.setMessage("Record update Sucessfully...");
			} catch (Exception e) {
				e.printStackTrace();
				result.setStatus(400);
				result.setMessage("Failed to save tracking record...");
			}
		}
		return result;
	}

}
