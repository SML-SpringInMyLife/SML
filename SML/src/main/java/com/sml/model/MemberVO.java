package com.sml.model;

import java.util.Date;

import lombok.Data;

@Data
public class MemberVO {
	//ȸ������ �Է»��� : ���̵�,��й�ȣ,�̸�,����,����ó,��󿬶�ó,�ּ�,�������
	private Long memCode;
	private String memId;
	private String memPw;
    private String memName;
    private String memMail;
    private String memPhone;
    private String memEmerPhone;
    
    //ȸ�� �ּ�
    private String memAddr1;
    private String memAddr2;
    private String memAddr3;
    
    //ȸ�� �������
    private Date memBirth;
    
    //ȸ�� ������
    private Date memJoinDate;
    
    //ȸ�� Ż����
    private Date memQuitDate;
    
    private Integer memStatus;
    
    //ȸ�� ����Ʈ
    private Integer memTotalPoint;
    
    //������ ����(0:�Ϲݻ����, 1:������)
    private Integer memAdminCheck;
    
    private Integer pointCode;

<<<<<<< HEAD
    test
=======
>>>>>>> 66bcad6a7d022d3d93afb90b7b4acd81356dd71f
	
}
