package com.sml.service;

import java.util.List;

import com.sml.model.CourseApplyVO;
import com.sml.model.Criteria;

public interface CourseApplyService {
	
	// 수강 신청
	public void applyApply(CourseApplyVO apply);
	
	// 리스트 보기
	public List<CourseApplyVO> applyList(Criteria cri);
	
	public int applyTotal(Criteria cri);
}
