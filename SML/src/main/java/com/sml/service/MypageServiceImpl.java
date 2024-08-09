package com.sml.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sml.mapper.MypageMapper;
import com.sml.model.MemberCheckVO;

@Service
public class MypageServiceImpl implements MypageService {
	
	@Autowired
    private MypageMapper mypageMapper;

    @Override
    public void insertMemberCheck(MemberCheckVO memberCheck){
        mypageMapper.insertMemberCheck(memberCheck);
    }

    
    //@Override
    //public List<MemberCheckVO> getMemberCheck() {
      //  return mypageMapper.selectMemberCheck();
    //}
}

