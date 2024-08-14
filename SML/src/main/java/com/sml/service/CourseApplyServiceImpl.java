package com.sml.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sml.mapper.CourseApplyMapper;
import com.sml.model.CourseApplyVO;
import com.sml.model.Criteria;

import lombok.extern.log4j.Log4j;

@Service
@Log4j
public class CourseApplyServiceImpl implements CourseApplyService {
	
	@Autowired
	CourseApplyMapper mapper;

	@Override
	public List<CourseApplyVO> applyList(Criteria cri) {
		return mapper.applyList(cri);
	}

	@Override
	public int applyTotal(Criteria cri) {
		return mapper.applyTotal(cri);
	}

	@Override
	public void applyApply(CourseApplyVO apply) {
		mapper.applyApply(apply);		
	}
	
}
