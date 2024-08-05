package com.sml.model;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class MemberVO {
	
	private int memCode;
	private String memId;
	private String memPw;
    private String memName;
    private String memMail;
    private String memPhone;
    private String memEmerPhone;
    
    
    private String memAddr1;
    private String memAddr2;
    private String memAddr3;
    
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date memBirth;
    
    //가입일
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date memJoinDate;
    
    //탈퇴일
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date memQuitDate;
    
    private int memStatus;    
    
    private int memTotalPoint;
    
    //권한체크(관리자 = 1, 회원 = 0)
    private int memAdminCheck;
    
    
}