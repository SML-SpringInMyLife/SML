package com.sml.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.sml.model.MemberCheckVO;
import com.sml.model.MemberVO;
import com.sml.model.PointVO;

public interface MypageMapper {

	//회원정보 수정
	public void updateMember(MemberVO member);
		
	// 출석체크 등록
    public void insertMemberCheck(MemberCheckVO memberCheck);

    // 출석체크 조회
    //List<MemberCheck> selectMemberCheck();
	
    //포인트 조회
    public List<PointVO> selectPointList(@Param("memCode")int memCode
		                               ,@Param("selectDate")String selectDate);
   
    //잔여포인트 조회
    public int selectTotalPoint(int memCode);
    
}
