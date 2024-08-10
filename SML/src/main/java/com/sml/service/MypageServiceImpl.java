package com.sml.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sml.mapper.MypageMapper;
import com.sml.model.MemberCheckVO;
import com.sml.model.MemberVO;
import com.sml.model.PointVO;

@Service
public class MypageServiceImpl implements MypageService {
	
	@Autowired
    private MypageMapper mypageMapper;
	
	//회원정보 수정
    @Override
    public void updateMember(MemberVO member) throws Exception {
    	mypageMapper.updateMember(member);
    }

    @Override
    public void insertMemberCheck(MemberCheckVO memberCheck){
        mypageMapper.insertMemberCheck(memberCheck);
    }

    
    //@Override
    //public List<MemberCheckVO> getMemberCheck() {
      //  return mypageMapper.selectMemberCheck();
    //}
    
    //포인트 조회
    @Override
    public List<PointVO> selectPointList(int memCode,String selectDate)throws Exception{
    	return mypageMapper.selectPointList(memCode,selectDate);
    
    }
    	
    //잔여포인트 조회
    @Override
    public int selectTotalPoint(int memCode)throws Exception{
    	return mypageMapper.selectTotalPoint(memCode);
    };
    
}

