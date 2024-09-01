package com.sml.model;

import java.util.Date;
import java.util.List;

import lombok.Data;
@Data
public class SaftyVO {

	private int saftyCode;
	private int memCode;
	private String memName;
	private Integer categoryCode;
	private String categoryName;
	private String saftyTitle;
	private String saftyBody;
	private Date saftyEnroll;
	private Date saftyModify;
	private String saftyStatus;
	private Integer saftyCount;
	private List<FileupVO> imageList;
	private boolean userLiked;
	private int saftyLike;
}
