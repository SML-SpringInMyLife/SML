package com.sml.model;

import java.util.Date;
import java.util.List;

import lombok.Data;

@Data
public class NoticeVO {
     
	private int noticeCode;
	private int memCode;
	private String memName;
	private Integer categoryCode;
	private String categoryName;
	private String noticeTitle;
	private String noticeBody;
	private Date noticeEnroll;
	private Date noticeModify;
	private String noticeStatus;
	private int noticeCount;
	private List<FileupVO> imageList;
	private boolean userLiked;
    private int noticeLike;
	
}
