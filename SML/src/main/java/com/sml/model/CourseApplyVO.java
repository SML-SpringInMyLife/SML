package com.sml.model;

import java.util.Date;

import lombok.Data;

@Data
public class CourseApplyVO {
	
	/* 이러면 관리자 단에서 볼 수 있는 그거잖아...........
	 * applyDate 신청한 날짜
	 * memCode 수강 신청한 사람 가져오기
	 * memName 수강 신청한 사람의 이름(select 용, NOT IN DB)
	 * courseCode 수강신청한 해당 수업 가져오기
	 * courseName 수강신청한 해당 수업의 이름 가져오기(select 용, NOT IN DB)
	 * courseStartDate 수강신청한 해당 수업의 개강일 가져오기 (select 용, NOT IN DB)
	 * applyCancel 취소 날짜
	 * applyStatus status
	 * */
	
	private int applyCode;
	private Date applyDate;
	private int memCode;
	private String memName;
	private int courseCode;
	private String courseName;
	private String courseStartDate;
	private Date applyCancel;
	private String applyStatus;

}
