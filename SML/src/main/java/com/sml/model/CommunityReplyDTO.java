package com.sml.model;

import java.util.Date;

import lombok.Data;

@Data
public class CommunityReplyDTO {
	
	private int repCode;
	private int memCode;
	private String repContent;
	private String targetCode;
	private int commCode;
	private int repGroup;
	private String repStatus;
	private Date renrollDate;
	private Date rmodifyDate;
	
	private String repWriter;

}
