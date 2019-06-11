package com.yooyoo.serviceImpl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.yooyoo.model.Fees;
import com.yooyoo.model.School;
import com.yooyoo.model.Student;
import com.yooyoo.repository.FeeRepository;
import com.yooyoo.repository.SchoolRepository;
import com.yooyoo.repository.StudentRepository;
import com.yooyoo.service.FeesService;
import com.yooyoo.util.VOMapper;
import com.yooyoo.vo.FeesVO;
import com.yooyoo.vo.StudentFeeVO;

@Service
public class FeeServiceImpl implements FeesService {

	@Autowired
	FeeRepository repository;
	
	@Autowired
	SchoolRepository schoolRepo;
	
	@Autowired
	StudentRepository studentRepo;

	@Override
	public void saveFeesForStudent(FeesVO fees) {
		Optional<School> school = schoolRepo.findById(fees.getSchoolId());
		Student student = studentRepo.findById(fees.getStudentId());
		Fees fee = VOMapper.getFeesModel(fees);
		fee.setStudent(student);
		fee.setSchool(school.get());
		fee.setDeleted("N");
		repository.save(fee);

	}

	@Override
	public void updateFees(FeesVO fee) {
		Optional<Fees> opt = null;
		if(fee.getId() != null){
			opt = repository.findById(fee.getId());
			if(opt.isPresent()){
				Optional<School> school = schoolRepo.findById(fee.getSchoolId());
				Student student = studentRepo.findById(fee.getStudentId());
				Fees fees = VOMapper.getFeesModel(fee);
				fees.setStudent(student);
				fees.setSchool(school.get());
				fees.setDeleted("N");
				repository.save(fees);
				
			}
		}
		

	}

	@Override
	public void deleteFees(int feeId) {
		Optional<Fees> fee = repository.findById(feeId);
		Fees f = fee.get();
		f.setDeleted("Y");
		repository.save(f);

	}

	@Override
	public List<FeesVO> getAllFeesBySchool(int schoolId) {
		Set<Fees> fees = repository.getFeesBySchool(schoolId);
		List<FeesVO> feeList = new ArrayList<>();
		for (Fees fee : fees) {
			FeesVO vo = VOMapper.getFeesVO(fee);
			feeList.add(vo);
		}
		return feeList;

	}

	@Override
	public void saveImageFile(int id, MultipartFile file) throws Exception {
		// Normalize file name
		String fileName = StringUtils.cleanPath(file.getOriginalFilename());
		try {
			// Check if the file's name contains invalid characters
			if (fileName.contains("..")) {
				throw new Exception("Sorry! Filename contains invalid path sequence " + fileName);
			}

			Optional<Fees> fee = repository.findById(id);
			Fees fees = fee.get();
			fees.setImage1(file.getBytes());
			repository.save(fees);
		} catch (IOException ex) {
			throw new Exception("Could not store file " + fileName + ". Please try again!", ex);
		}

	}

	@Override
	public FeesVO getFeesByStudent(StudentFeeVO feeVO) {
		FeesVO feesVO = null;
		List<Fees> fees = repository.findByStudentId(feeVO.getId());
		if (fees != null && !fees.isEmpty()) {
			feesVO = VOMapper.getFeesVO(fees.get(0));
		}
		return feesVO;
	}

}
