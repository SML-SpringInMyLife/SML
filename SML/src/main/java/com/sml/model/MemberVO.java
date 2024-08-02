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
    
    private int memStatus;
    
    //ȸ�� ����Ʈ
    private int memTotalPoint;
    
    //������ ����(0:�Ϲݻ����, 1:������)
    private int memAdminCheck;
    
    private int pointCode;
}
