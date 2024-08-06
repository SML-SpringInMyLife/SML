package com.sml.model;

import lombok.Data;

@Data
public class FileupVO {
 
	private int fileCode;
	private int noticeCode;
	private int saftyCode;
	private String fileName;
	private String filePath;
	private String fileType;
}
