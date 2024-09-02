package com.sml.mapper;

import java.util.List;

import com.sml.model.CourseApplyDTO;
import com.sml.model.MemberVO;

public interface CourseApplyMapper {

	public int enrollApply(CourseApplyDTO apply) throws Exception;
	
	public int modifyApply(CourseApplyDTO apply);
	
	public List<CourseApplyDTO> getApply(int memCode);
	
	public CourseApplyDTO checkApply(CourseApplyDTO apply);
	
	// point limit
	public int coursePoint(MemberVO member);
	
	public int getMemTotalPoint(int memCode);
	
	public int getCoursePoint(int courseCode);
	
	// ¼ö°­»ý limit
	public int getApplyLimit(int applyCode);
	public int getCourseLimit(int courseCode);
}
