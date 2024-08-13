package com.sml.model;

import java.util.Date;

import lombok.Data;

@Data
public class ChatVO {

	private int chatCode; // 채팅 코드
	private int memCode; // 보낸 사람의 코드
	private int categoryCode; // 상담 카테고리 코드
	private String chatContent; // 채팅 내용
	private Date chatDate; // 채팅 날짜
	private int status; // 상태 (1: 활성, 0: 비활성)

}
