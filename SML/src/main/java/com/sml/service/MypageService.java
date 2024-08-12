package com.sml.service;

import java.util.List;

import com.sml.model.MemberCheckVO;
import com.sml.model.MemberVO;
import com.sml.model.PointVO;

public interface MypageService {
	
	//회원정보수정
	public void updateMember(MemberVO member) throws Exception;
	
	//출석체크 
	public void insertMemberCheck(MemberCheckVO memberCheck);
	 //public List<MemberCheck> getMemberCheck();
	
	//포인트 조회
	public List<PointVO> selectPointList(int memCode,String selectDate) throws Exception;
	
	//잔여포인트 조회
	public int selectTotalPoint(int memCode)throws Exception;
			
		
		
	
}
