package com.sml.service;

import java.util.List;

import com.sml.model.MemberVO;
import com.sml.model.PointVO;

public interface MemberService {
	
	//회원가입
	public void MemberJoin(MemberVO member) throws Exception;
	
	//아이디 중복 검사
	public int idCheck(String memberId) throws Exception;

	//로그인
	public MemberVO memberLogin(MemberVO member) throws Exception;
	
	
}
