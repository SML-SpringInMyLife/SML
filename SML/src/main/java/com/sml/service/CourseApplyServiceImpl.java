package com.sml.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sml.mapper.CourseApplyMapper;
import com.sml.model.CourseApplyDTO;
import com.sml.model.MemberVO;

import lombok.extern.log4j.Log4j;

@Service
@Log4j
public class CourseApplyServiceImpl implements CourseApplyService {
	
	@Autowired
	CourseApplyMapper applyMapper;

	@Override
	public int enrollApply(CourseApplyDTO apply) {
		CourseApplyDTO checkApply = applyMapper.checkApply(apply);
		int memPoint = applyMapper.getMemTotalPoint(apply.getMemCode());
        int coursePoint = applyMapper.getCoursePoint(apply.getCourseCode());
        int applyLimit = applyMapper.getApplyLimit(apply.getApplyCode());
        int courseLimit = applyMapper.getCourseLimit(apply.getCourseCode());
		
		if(checkApply != null) {
			return 2;
		}
		
		if(memPoint < coursePoint) {
			return 4;
		}
		
		if(applyLimit > courseLimit) {
			return 6;
		}
		
		try {
			return applyMapper.enrollApply(apply);
		} catch (Exception e) {
			return 0;
		}
	}

	@Override
	public int coursePoint(MemberVO member) throws Exception {
		return applyMapper.coursePoint(member);
	}

	@Override
	public int getMemTotalPoint(int memCode) throws Exception {
		return applyMapper.getMemTotalPoint(memCode);
	}

	@Override
	public int getCoursePoint(int courseCode) throws Exception {
		return applyMapper.getCoursePoint(courseCode);
	}

	@Override
	public int getCourseLimit(int courseCode) throws Exception {
		return applyMapper.getCourseLimit(courseCode);
	}

	@Override
	public int getApplyLimit(int applyCode) throws Exception {
		return applyMapper.getApplyLimit(applyCode);
	}
	
}
