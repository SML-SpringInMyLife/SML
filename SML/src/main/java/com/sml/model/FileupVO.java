package com.sml.model;

import lombok.Data;

@Data
public class FileupVO {
 
	private int fileCode;
	private int noticeCode;
	private int saftyCode;
	/* 파일이름 */
	private String fileName;
	/* 파일경로 */
	private String filePath;
	/* 파일중복이름 */
	private String fileUuid;
	/* 파일 타입선택 */
	private String fileType;
}
