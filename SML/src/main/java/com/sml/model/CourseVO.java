package com.sml.model;

import java.util.Date;

import lombok.Data;

@Data
public class CourseVO {
	
	private int courseCode;
	private int ccatCode;
	private String ccatName;
	private String courseName;
	private String courseContent;
	private int courseLimit;
	private Date startDate;
	private Date endDate;
	private int teaCode;
	private String teaName;
	private Date enrollDate;
	private Date modifyDate;
	private int coursePoint;
	private String courseStatus;
	private String startTime;
	private String endTime;
	private String courseDay;
	
}
