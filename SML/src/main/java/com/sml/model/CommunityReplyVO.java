package com.sml.model;

import java.util.Date;

import lombok.Data;

@Data
public class CommunityReplyVO {
	
	private int repCode;
	private int memCode;
	private int commCode;
	private String repContent;
	private Date renrollDate;
	private Date rmodifyDate;
	
	private String repWriter;
	private String repOwner;
}
