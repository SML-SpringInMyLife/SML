package com.sml.model;

import java.util.Date;

import lombok.Data;

@Data
public class CommunityReplyVO {
	
	private int repCode;
	private int commCode;
	private Integer repParentCode;
	private int repWriter;
	private String repContent;
	private Date renrollDate;
	private Date rmodifyDate;
	
}
