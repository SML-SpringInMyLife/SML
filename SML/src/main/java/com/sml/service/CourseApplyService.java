package com.sml.service;

import com.sml.model.CourseApplyDTO;
import com.sml.model.MemberVO;

public interface CourseApplyService {
	
	public void courseApply(CourseApplyDTO dto) throws Exception;
	
	public int coursePoint(MemberVO member) throws Exception;
}
