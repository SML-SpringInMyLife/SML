package com.sml.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sml.mapper.CourseTeacherMapper;
import com.sml.model.CourseTeacherVO;
import com.sml.model.Criteria;

import lombok.extern.log4j.Log4j;

@Service
@Log4j
public class CourseTeacherServiceImpl implements CourseTeacherService {
	
	@Autowired 
	CourseTeacherMapper mapper;

	@Override
	public void teacherEnroll(CourseTeacherVO teacher) throws Exception {
		mapper.teacherEnroll(teacher);		
	}

	@Override
	public List<CourseTeacherVO> teacherList(Criteria cri) throws Exception {
		return mapper.teacherList(cri);
	}

	@Override
	public int teacherListTotal(Criteria cri) throws Exception {
		log.info("(service)teacherListTotal()........." +cri);
		return mapper.teacherListTotal(cri);
	}

	@Override
	public CourseTeacherVO teacherDetail(int teaCode) throws Exception {
		log.info("teacherDetail......" +teaCode);
		return mapper.teacherDetail(teaCode);
	}

	@Override
	public int teacherModify(CourseTeacherVO teacher) throws Exception {
		log.info("(service) teacherModify....." +teacher);
		return mapper.teacherModify(teacher);
	}

	@Override
	public int teacherDelete(int teaCode) {
		log.info("teacherDelete..........");
		return mapper.teacherDelete(teaCode);
	}

}
