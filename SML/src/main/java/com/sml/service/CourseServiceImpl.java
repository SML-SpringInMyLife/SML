package com.sml.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sml.mapper.CourseMapper;
import com.sml.model.CourseVO;
import com.sml.model.Criteria;

import lombok.extern.log4j.Log4j;

@Service
@Log4j
public class CourseServiceImpl implements CourseService {
	
	@Autowired
	CourseMapper mapper;

	@Override
	public void courseEnroll(CourseVO course) {
		log.info("(service)enroll.........");
		mapper.courseEnroll(course);
	}

	@Override
	public List<CourseVO> courseList(Criteria cri) {
		log.info("수강신청 리스트..............");
		return mapper.courseList(cri);
	}

	@Override
	public int courseTotal(Criteria cri) {
		log.info("courseTotal()...........");
		return mapper.courseTotal(cri);
	}
}
