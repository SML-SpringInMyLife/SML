package com.sml.model;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class MemberVO {
<<<<<<< HEAD
	//È¸¿ø°¡ÀÔ ÀÔ·Â»çÇ× : ¾ÆÀÌµğ,ºñ¹Ğ¹øÈ£,ÀÌ¸§,¸ŞÀÏ,¿¬¶ôÃ³,ºñ»ó¿¬¶ôÃ³,ÁÖ¼Ò,»ı³â¿ùÀÏ
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
    
    //ê°€ì…ì¼
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date memJoinDate;
    
    //íƒˆí‡´ì¼
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date memQuitDate;
    
    private int memStatus;    
    
    private int memTotalPoint;
    
    //ê¶Œí•œì²´í¬(ê´€ë¦¬ì = 1, íšŒì› = 0)
    private int memAdminCheck;
    
    
}
