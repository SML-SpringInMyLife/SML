package com.sml.service;

import java.util.List;

import com.sml.model.CourseVO;
import com.sml.model.Criteria;

public interface CourseService {
	
	public void courseEnroll(CourseVO course);
	
	public List<CourseVO> courseList(Criteria cri);
	
	public int courseTotal(Criteria cri);

}
