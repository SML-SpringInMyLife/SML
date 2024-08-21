package com.sml.mapper;

import java.util.List;

import com.sml.model.CourseApplyDTO;
import com.sml.model.Criteria;

public interface CourseApplyMapper {
	
	/* 
	 * 수강 신청 (apply)
	 * 관리자 - 수강신청한 사람들 볼 수 있는 
	 * 마이 페이지 - 내 수강신청 목록들을 볼 수 있는
	 *           취소 
	 * */
	
	/*수강신청
	 * 수강 신청 버튼을 누르면 수강 신청이 되고 COURSE_APPLICANT 테이블에 쌓임
	 * 		로그인 시에만 수강신청할 수 있음
	 * 		COURSE 테이블의 COURSE를 참조해서 신청 
	 * 		redirect:"/member/mycourses"
	 * 포인트 차감 (COURSE 테이블의 COURSE_POINT 만큼 마이너스)
	 * 제한 (COURSE 테이블의 COURSE_LIMIT만큼 데이터가 차면 더 이상 버튼을 누를 수 없게)
	 * */
	public int courseApply(CourseApplyDTO apply) throws Exception;
	
	public int applyTotal(Criteria cri);
	
	public int cancelApply(int applyCode);
	
	public List<CourseApplyDTO> applyList(int memCode);
	
	public CourseApplyDTO applyCheck(CourseApplyDTO apply);
	
	
	// 수강신청 목록
//	public List<CourseApplyDTO> applyList(Criteria cri);
//	
//	public int applyTotal(Criteria cri);
	

}
