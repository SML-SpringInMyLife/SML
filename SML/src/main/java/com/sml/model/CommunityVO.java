package com.sml.model;

import java.util.Date;

import lombok.Data;

@Data
public class CommunityVO {
	
	private int commCode;
	private String commTitle;
	private String commWriter;
	private String commContent;
	private Date enrollDate;
	private Date modifyDate;
	private int commPoint;
	private int commSecret;
	private String commSecretPwd;
	private int commCount;
	private String commStatus;
	private int memberCode;

}
