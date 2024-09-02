package com.sml.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sml.mapper.CommunityReplyMapper;
import com.sml.model.CommunityReplyVO;

import lombok.extern.log4j.Log4j;

@Service
@Log4j
public class CommunityReplyServiceImpl implements CommunityReplyService {

	@Autowired
	CommunityReplyMapper replyMapper;
	
	@Override
	public int enrollReply(CommunityReplyVO reply) {
		int result = replyMapper.enrollReply(reply);
		return result;
	}

	@Override
	public String checkReply(CommunityReplyVO reply) {
		Integer result = replyMapper.checkReply(reply);
		
		if(result == null) {
			return "0";
		} else {
			return "1";
		}
	}

}
