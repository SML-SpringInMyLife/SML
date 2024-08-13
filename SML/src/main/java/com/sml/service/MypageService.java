package com.sml.service;

import java.util.List;

import com.sml.model.MemberCheckVO;
import com.sml.model.MemberVO;
import com.sml.model.PointVO;

public interface MypageService {
	
	//회원정보 수정
	public void updateMember(MemberVO member) throws Exception;
	
	//출석체크 등록
	public void insertMemberCheck(MemberCheckVO memberCheck);
	
	//출석체크 조회
	public List<MemberCheckVO> selectMemberCheckList(int memCode,String yearMonth)throws Exception;
	
	//출석체크 중복검사
	public int memberCheckTest(int memCode,String todayDate);
	
		
	//포인트 조회
	public List<PointVO> selectPointList(int memCode,String selectDate) throws Exception;
	
	//잔여포인트 조회
	public int selectTotalPoint(int memCode)throws Exception;
			
		
		
	
}
