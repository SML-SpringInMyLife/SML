package com.sml.service;

import com.sml.model.MemberVO;

public interface MemberService {
	
	//ȸ������
	public void MemberJoin(MemberVO member) throws Exception;
	
	//���̵� �ߺ� �˻�
	public int idCheck(String memberId) throws Exception;

	//�α���
	public MemberVO memberLogin(MemberVO member) throws Exception;
}
