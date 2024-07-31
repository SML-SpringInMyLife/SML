package com.sml.service;

import java.util.List;

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
	public List<CommunityVO> getBoardList() throws Exception {
		log.info("(service)getBoardList().....");
		return communityMapper.boardList();
	}
}
