package com.yooyoo.util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.web.multipart.MultipartFile;

import com.yooyoo.vo.SchoolInfo;
import com.yooyoo.vo.StudentVO;

public class FileUtils {
	public static List<StudentVO> readAllDataAtOnce(MultipartFile csvFile, int schoolId) {
		List<StudentVO> students = new ArrayList<>();
		try {
			// create csvReader object and skip first Line
			InputStream stream = csvFile.getInputStream();
			CsvReader reader = new CsvReader(new BufferedReader(new InputStreamReader(stream)));
			reader.readHeaders();
			while (reader.readRecord()) {
				StudentVO student = new StudentVO();
				
				String name = reader.get("Name");
				String grade = reader.get("Class");
				String fathersname = reader.get("Father's Name");
				String mothersName = reader.get("Mother's Name");
				String mobile = reader.get("Primary Mobile");
				String altMobile = reader.get("Alternate Mobile");
				String pemail = reader.get("Primary Email");
				String altEmail = reader.get("Alternate Email");
				String gender = reader.get("Gender");
				String dob = reader.get("Date Of Birth");
				String fProfession = reader.get("Father's Profession");
				String mProfession = reader.get("Mother's Profession");

				student.setSchoolId(schoolId);
				if (name != null && !name.isEmpty()) {
					student.setFirstName(name);
				}
				if (grade != null && !grade.isEmpty()) {
					if (grade.equalsIgnoreCase("Nursery")) {
						student.setGradeId(1);
					} else if (grade.equalsIgnoreCase("LKG")) {
						student.setGradeId(2);
					} else {
						student.setGradeId(3);
					}
				}
				if (fathersname != null && !fathersname.isEmpty()) {
					student.setFatherName(fathersname);
				}
				if (mothersName != null && !mothersName.isEmpty()) {
					student.setMotherName(mothersName);
				}
				if (mobile != null && !mobile.isEmpty()) {
					student.setParentMobile1(mobile);
				}
				if (altMobile != null && !altMobile.isEmpty()) {
					student.setParentMobile2(altMobile);
				}
				if (pemail != null && !pemail.isEmpty()) {
					student.setPrimaryEmail(pemail);
				}
				if (altEmail != null && !altEmail.isEmpty()) {
					student.setSecondaryEmail(altEmail);
				}

				if (gender != null && !gender.isEmpty()) {
					student.setGender(gender);
				}

				if (dob != null && !dob.isEmpty()) {
					student.setDob(dob);
				}

				if (fProfession != null && !fProfession.isEmpty()) {
					student.setFatherProfession(fProfession);
				}

				if (mProfession != null && !mProfession.isEmpty()) {
					student.setMotherProfession(mProfession);
				}
				if (!checkNullOrEmpty(name) && !checkNullOrEmpty(grade) && !checkNullOrEmpty(fathersname)
						&& !checkNullOrEmpty(mothersName) && !checkNullOrEmpty(mobile) && !checkNullOrEmpty(pemail)) {
					students.add(student);
				}

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return students;
	}
	
	public static List<SchoolInfo> readAllDataAtOnceForSchool(MultipartFile csvFile) {
		List<SchoolInfo> schools = new ArrayList<>();
		try {
			// create csvReader object and skip first Line
			InputStream stream = csvFile.getInputStream();
			CsvReader reader = new CsvReader(new BufferedReader(new InputStreamReader(stream)));
			reader.readHeaders();
			while (reader.readRecord()) {
				SchoolInfo school = new SchoolInfo();
				
				String name = reader.get("Name");
				String address = reader.get("Address");
				String state = reader.get("State");
				String country = reader.get("Country");
				String pin = reader.get("Pin");
				String ownerName = reader.get("Owner_Name");
				String ownerMobile = reader.get("Owner_Mobile");
				String ownerEmail = reader.get("Owner_Email");
				String registration = reader.get("Registration");
				String enableFees = reader.get("Enable_Fees");
				String enableAttandance = reader.get("Enable_Attandance");
				String enableWorkSheet = reader.get("Enable_WorkSheet");
				if (name != null && !name.isEmpty()) {
					school.setName(name);
				}
				if (address != null && !address.isEmpty()) {
					school.setAddress(address);
				}
				if (state != null && !state.isEmpty()) {
					school.setState(state);
				}
				if (country != null && !country.isEmpty()) {
					school.setCountry(country);
				}
				if (pin != null && !pin.isEmpty()) {
					school.setPin(pin);
				}
				if (ownerName != null && !ownerName.isEmpty()) {
					school.setOwnerName(ownerName);
				}

				if (ownerMobile != null && !ownerMobile.isEmpty()) {
					school.setOwnerMobile(ownerMobile);
				}

				if (ownerEmail != null && !ownerEmail.isEmpty()) {
					school.setEmailId(ownerEmail);
				}

				if (registration != null && !registration.isEmpty()) {
					school.setRegistrationName(registration);
				}
				if (enableFees != null && !enableFees.isEmpty()) {
					school.setEnableFees(Integer.valueOf(enableFees));
				}
				if (enableAttandance != null && !enableAttandance.isEmpty()) {
					school.setEnableAttendance(Integer.valueOf(enableAttandance));
				}
				if (enableWorkSheet != null && !enableWorkSheet.isEmpty()) {
					school.setEnablePrintedWorksheet(Integer.valueOf(enableWorkSheet));
				}
				
				school.setCode(gen());
				if (!checkNullOrEmpty(name) && !checkNullOrEmpty(address) && !checkNullOrEmpty(registration)
						&& !checkNullOrEmpty(ownerName) && !checkNullOrEmpty(ownerEmail) && !checkNullOrEmpty(ownerMobile)) {
					schools.add(school);
				}

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return schools;
	}

	private static boolean checkNullOrEmpty(String name) {
		boolean status = true;
		if (name != null && !name.isEmpty()) {
			status = false;
		}
		return status;
	}
	
	public static String checkFileExtension(String name){
		int lastIndexOf = name.lastIndexOf(".");
	    if (lastIndexOf == -1) {
	        return ""; // empty extension
	    }
	    return name.substring(lastIndexOf);
	}
	
	public static int gen() {
	    Random r = new Random( System.currentTimeMillis() );
	    return ((1 + r.nextInt(2)) * 1000 + r.nextInt(1000));
	}

}
