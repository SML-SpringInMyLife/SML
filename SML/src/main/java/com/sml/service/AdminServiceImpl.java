package com.sml.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sml.mapper.AdminMapper;
import com.sml.model.MemberVO;

import lombok.extern.log4j.Log4j;

@Service
@Log4j
public class AdminServiceImpl implements AdminService {

	@Autowired
	AdminMapper adminMapper;

	@Override
	public List<MemberVO> getMemberList() throws Exception {
		return adminMapper.getMemberList();
	}

	@Override
	public void updateStatus(@Param("memCode") int memCode, @Param("memStatus") int memStatus) throws Exception {
		adminMapper.updateStatus(memCode, memStatus);
	}

	@Override
	public int getMemberCnt() throws Exception {
		return adminMapper.getMemberCnt();
	}
	
	@Override
    public Map<String, Integer> getAgeGroupCnt() throws Exception {
        return adminMapper.getAgeGroupCnt();
    }

	/*
	 * @Override public Map<String, int[]> getAgeGroupCountsByMonth(String year) {
	 * return adminMapper.getMonthlyAgeGroupCounts(); }
	 */

	
	
	
}
