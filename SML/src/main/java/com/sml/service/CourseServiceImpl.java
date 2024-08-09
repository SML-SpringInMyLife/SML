package com.sml.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sml.mapper.CourseMapper;
import com.sml.model.CourseVO;

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
}
