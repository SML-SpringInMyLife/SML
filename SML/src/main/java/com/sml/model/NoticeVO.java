package com.sml.model;

import java.util.Date;
import java.util.List;

import lombok.Data;

@Data
public class NoticeVO {
     
	private int noticeCode;
	private int memCode;
	private Integer categoryCode;
	private String categoryName;
	private String noticeTitle;
	private String noticeBody;
	private Date noticeEnroll;
	private Date noticeModify;
	private int noticeLike;
	private String noticeStatus;
	private int noticeCount;
	private String memName;
	private List<FileupVO> imageList;

	
}
