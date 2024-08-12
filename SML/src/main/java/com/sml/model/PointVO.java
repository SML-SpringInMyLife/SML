package com.sml.model;

import java.util.Date;

import lombok.Data;

@Data
public class PointVO {
	
	private int pointCode;
	private int pointPrice;	
	private Date pointDate;
	private String pointComment;
	private int status;
	private int memCode;
}	
	
	
	
	
	
	
	
	