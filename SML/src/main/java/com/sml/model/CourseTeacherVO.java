package com.sml.model;

import java.util.Date;

import lombok.Data;

@Data
public class CourseTeacherVO {
	
	private int teaCode;
	private String teaName;
	private String teaIntro;
	private String teaStatus;
	private Date teaEnrollDate;
	private Date teaModifyDate;

}
