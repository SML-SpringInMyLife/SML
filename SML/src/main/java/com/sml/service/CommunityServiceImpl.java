package com.sml.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sml.mapper.CommunityMapper;

import lombok.extern.log4j.Log4j;

@Service
@Log4j
public class CommunityServiceImpl implements CommunityService{

	@Autowired
	CommunityMapper communityMapper;
}
