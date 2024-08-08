package com.sml.mapper;

import com.sml.model.MemberCheckVO;

public interface MypageMapper {

	
	// 출석체크 등록
    public void insertMemberCheck(MemberCheckVO memberCheck);

    // 출석체크 조회
    //List<MemberCheck> selectMemberCheck();
	
}
