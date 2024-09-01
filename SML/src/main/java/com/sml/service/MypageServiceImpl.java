package com.sml.service;

import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sml.mapper.MypageMapper;
import com.sml.model.CourseApplyDTO;
import com.sml.model.Criteria;
import com.sml.model.MemberCheckVO;
import com.sml.model.MemberVO;
import com.sml.model.PageDTO;
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
    
    //출석체크 등록
    @Override
    public void insertMemberCheck(MemberCheckVO memberCheck){
        mypageMapper.insertMemberCheck(memberCheck);
    }

    //출석체크 조회
    @Override
    public List<MemberCheckVO> selectMemberCheckList(int memCode,String selectDate)throws Exception {
        return mypageMapper.selectMemberCheckList(memCode,selectDate);
   }
    
    //출석체크 중복검사
   	@Override
   	public int memberCheckTest(int memCode,String todayDate){   	
   		return mypageMapper.memberCheckTest(memCode,todayDate);
   	}
    
    
    
    //포인트 조회
    @Override
    public List<PointVO> selectPointList(int memCode,String selectDate, Criteria criteria)throws Exception{
    	return mypageMapper.selectPointList(memCode,selectDate,criteria);
    
    }
    
    @Override
    public PageDTO getTotalCount(int memCode, String selectDate, Criteria criteria) {
        int total = mypageMapper.getTotalCount(memCode, selectDate);
        return new PageDTO(criteria, total);
    }
    
   //포인트 등록
    @Override
  	public void insertPoint(PointVO point){
    	mypageMapper.insertPoint(point);
    }
    
    //잔여포인트 조회
    @Override
    public int selectTotalPoint(int memCode)throws Exception{
    	return mypageMapper.selectTotalPoint(memCode);
    }
    
    //잔여포인트 업데이트 
    @Override
    public void updateTotalPoint(MemberVO member)throws Exception{
    	mypageMapper.updateTotalPoint(member);
    }
    //수강목록 조회
    @Override
    public List<CourseApplyDTO> selectCourseList(int memCode, Criteria criteria)throws Exception{
    	return mypageMapper.selectCourseList(memCode,criteria);
    }
    @Override
    public PageDTO getCourseTotalCount(int memCode, Criteria criteria) {
        int total = mypageMapper.getCourseTotalCount(memCode);
        return new PageDTO(criteria, total);
    }	
    
    
}

