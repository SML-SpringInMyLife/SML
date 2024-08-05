package com.sml.model;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class MemberVO {
<<<<<<< HEAD
	//회원가입 입력사항 : 아이디,비밀번호,이름,메일,연락처,비상연락처,주소,생년월일
=======
	
>>>>>>> member
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
    
    //媛��엯�씪
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date memJoinDate;
    
    //�깉�눜�씪
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date memQuitDate;
    
    private int memStatus;    
    
    private int memTotalPoint;
    
    //沅뚰븳泥댄겕(愿�由ъ옄 = 1, �쉶�썝 = 0)
    private int memAdminCheck;
    
    
}
