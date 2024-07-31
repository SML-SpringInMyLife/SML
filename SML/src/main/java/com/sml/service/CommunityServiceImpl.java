package com.sml.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sml.mapper.CommunityMapper;
import com.sml.model.CommunityVO;

import lombok.extern.log4j.Log4j;

@Service
@Log4j
public class CommunityServiceImpl implements CommunityService{

	@Autowired
	CommunityMapper communityMapper;

	@Override
	public void communityEnroll(CommunityVO community) throws Exception {
		// TODO Auto-generated method stub
		
	}
}
