package com.sml.service;

import com.sml.model.MemberVO;

public interface MemberService {
	
	public void MemberJoin(MemberVO member) throws Exception;
	
	public int idCheck(String memberId) throws Exception;

	public MemberVO memberLogin(MemberVO member) throws Exception;
}
