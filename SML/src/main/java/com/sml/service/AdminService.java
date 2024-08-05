package com.sml.service;

import java.util.List;

import com.sml.model.MemberVO;

public interface AdminService {

	public List<MemberVO> getMemberList() throws Exception;
	
}
