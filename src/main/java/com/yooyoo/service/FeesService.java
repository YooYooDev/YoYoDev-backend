package com.yooyoo.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.yooyoo.vo.FeesVO;
import com.yooyoo.vo.StudentFeeVO;

public interface FeesService {
	
	public void saveFeesForStudent(FeesVO fee);
	
	public void  updateFees(FeesVO fee);
	
	public void deleteFees(int feeId);
	
	public List<FeesVO> getAllFeesBySchool(int schoolId);
	
	void saveImageFile(int id, MultipartFile file)throws Exception;

	public FeesVO getFeesByStudent(StudentFeeVO feeVO);

}
