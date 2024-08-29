package com.sml.model;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class NoticeLikeVO {
	private Long likeId;
	private int noticeCode;
	private Integer memCode; // null 가능 (비회원의 경우)
	private String guestIdentifier; // null 가능 (회원의 경우)
	private boolean likeStatus;
	private LocalDateTime createdAt;

}
