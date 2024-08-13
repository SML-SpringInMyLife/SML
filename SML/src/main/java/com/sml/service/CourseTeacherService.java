package com.sml.service;

import java.util.List;

import com.sml.model.CourseTeacherVO;
import com.sml.model.Criteria;

public interface CourseTeacherService {
	
	public void teacherEnroll(CourseTeacherVO teacher) throws Exception;
	
	public List<CourseTeacherVO> teacherList(Criteria cri) throws Exception;
	
	public int teacherListTotal(Criteria cri) throws Exception;
	
	public CourseTeacherVO teacherDetail(int teaCode) throws Exception;
	
	public int teacherModify(CourseTeacherVO teacher) throws Exception;
	
	public int teacherDelete(int teaCode);

}
