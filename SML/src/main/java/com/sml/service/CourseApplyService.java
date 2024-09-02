package com.sml.service;

import com.sml.model.CourseApplyDTO;
import com.sml.model.MemberVO;

public interface CourseApplyService {
	
	public int enrollApply(CourseApplyDTO apply);
	
	public int coursePoint(MemberVO member) throws Exception;
	
	public int getMemTotalPoint(int memCode) throws Exception;
	
	public int getCoursePoint(int courseCode) throws Exception;
	
	public int getApplyLimit(int applyCode) throws Exception;
	
	public int getCourseLimit(int courseCode) throws Exception;

}
