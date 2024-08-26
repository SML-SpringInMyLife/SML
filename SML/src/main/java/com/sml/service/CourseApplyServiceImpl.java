package com.sml.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sml.mapper.CourseApplyMapper;
import com.sml.model.CourseApplyDTO;
import com.sml.model.Criteria;
import com.sml.model.MemberVO;

import lombok.extern.log4j.Log4j;

@Service
@Log4j
public class CourseApplyServiceImpl implements CourseApplyService {
	
	@Autowired
	CourseApplyMapper mapper;

	@Override
	public void courseApply(CourseApplyDTO dto) throws Exception {
		mapper.courseApply(dto);		
	}

	@Override
	public int coursePoint(MemberVO member) throws Exception {
		return mapper.coursePoint(member);
	}

	
}
