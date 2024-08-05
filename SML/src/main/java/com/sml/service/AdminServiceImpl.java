package com.sml.service;

import java.util.List;

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

	
	

}
