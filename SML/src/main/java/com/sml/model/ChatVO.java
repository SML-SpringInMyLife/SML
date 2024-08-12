package com.sml.model;

import java.util.Date;

import lombok.Data;

@Data
public class ChatVO {

	private int chatCode; // 채팅 코드
	private int chatRoomCode; // 채팅방 코드
	private int senderCode; // 보낸 사람의 코드
	private String chatContent; // 채팅 내용
	private String chatType; // 채팅 유형 (TEXT, IMAGE, FILE 등)
	private Date chatDate; // 채팅 날짜
	private int isRead; // 읽음 여부 (0: 안 읽음, 1: 읽음)
	private int status; // 상태 (1: 활성, 0: 비활성)

}
