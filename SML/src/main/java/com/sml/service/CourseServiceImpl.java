package com.sml.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sml.mapper.CourseMapper;
import com.sml.model.CourseCategoryVO;
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
	public List<CourseCategoryVO> cateList() {
		log.info("(service) cateList......");
		return mapper.cateList();
	}

	@Override
	public List<CourseVO> courseList(Criteria cri) {
		log.info("�닔媛뺤떊泥� 由ъ뒪�듃..............");
		return mapper.courseList(cri);
	}

	@Override
	public int courseTotal(Criteria cri) {
		log.info("courseTotal()...........");
		return mapper.courseTotal(cri);
	}

	@Override
	public CourseVO courseDetail(int courseCode) {
		log.info("�긽�꽭 �럹�씠吏� 吏꾩엯" +courseCode);
		return mapper.courseDetail(courseCode);
	}

	@Override
	public int courseModify(CourseVO course) throws Exception {
		log.info("(service) courseModify........." +course);
		return mapper.courseModify(course);
	}

	@Override
	public int courseDelete(int courseCode) {
		return mapper.courseDelete(courseCode);
	}

	@Override
	public CourseVO courseApplyDetail(int courseCode) {
		return mapper.courseApplyDetail(courseCode);
	}

	@Override
	public int courseApply(int applyCode) {
		return mapper.courseApply(applyCode);
	}

	
}
