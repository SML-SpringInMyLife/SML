package com.sml.model;

import java.util.Date;

import lombok.Data;

@Data
public class MemberCheckVO {
	
	 private int checkCode;
	 private Date checkDate;
	 private int status;  
	 private int memCode;

}
