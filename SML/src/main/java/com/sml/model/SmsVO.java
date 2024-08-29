package com.sml.model;

import lombok.Data;
import java.util.Date;

@Data
public class SmsVO {

	private int smsCode; // SMS_CODE: 고유 식별자
	private String smsContent; // SMS_CONTENT: SMS 내용
	private Date sendDate; // SEND_DATE: 발송 일자
	private int status; // STATUS: 상태 (예: 발송 완료, 대기 중 등)
	private int memCode; // MEM_CODE: 회원 코드 (외래키)
	private String memName; // 추가된 필드
	private String memId; // 추가된 필드

}