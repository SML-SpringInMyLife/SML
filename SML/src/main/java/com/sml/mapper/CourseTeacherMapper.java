package com.sml.mapper;

import java.util.List;

import com.sml.model.CourseTeacherVO;
import com.sml.model.Criteria;

public interface CourseTeacherMapper {
	
	public void teacherEnroll(CourseTeacherVO teacher);
	
	public List<CourseTeacherVO> teacherList(Criteria cri);
	
	public int teacherListTotal(Criteria cri);
	
	public CourseTeacherVO teacherDetail(int teaCode);
	
	public int teacherModify(CourseTeacherVO teacher);
	
	public int teacherDelete(int teaCode);
	
}
