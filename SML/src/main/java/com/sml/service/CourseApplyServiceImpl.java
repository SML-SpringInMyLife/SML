package com.sml.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sml.mapper.CourseApplyMapper;
import com.sml.model.CourseApplyDTO;
import com.sml.model.Criteria;

import lombok.extern.log4j.Log4j;

@Service
@Log4j
public class CourseApplyServiceImpl implements CourseApplyService {
	
	@Autowired
	CourseApplyMapper mapper;

	@Override
	public int applyApply(CourseApplyDTO apply) {
		
		CourseApplyDTO applyCheck = mapper.applyCheck(apply);
		
		if(applyCheck != null) {
			return 2;
		}
		
		try {
			return mapper.courseApply(apply);
		} catch (Exception e) {
			return 0;
		}
		
	}

	
	
}
