package com.sml.model;

import java.util.Date;

import lombok.Data;

@Data
public class MemberVO {
	
	private Long memCode;
	private String memId;
	private String memPw;
    private String memName;
    private String memMail;
    private String memPhone;
    private String memEmerPhone;
    
    //회원 주소
    private String memAddr1;
    private String memAddr2;
    private String memAddr3;
    
    //회원 생년월일
    private Date memBirth;
    
    //회원 가입일
    private Date memJoinDate;
    
    //회원 탈퇴일
    private Date memQuitDate;
    
    private Integer memStatus;
    
    //회원 포인트
    private Integer memTotalPoint;
    
    //관리자 구분(0:일반사용자, 1:관리자)
    private Integer memAdminCheck;
    
    private Integer pointCode;

	
}
