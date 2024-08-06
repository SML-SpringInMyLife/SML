package com.sml.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sml.mapper.CommunityReplyMapper;
import com.sml.model.ReplyDTO;

@Service
public class CommunityReplyServiceImpl implements CommunityReplyService {
	
	@Autowired
	private CommunityReplyMapper replyMapper;

	@Override
	public int enrollReply(ReplyDTO dto) {
		int result = replyMapper.enrollReply(dto);
		return result;
	}

}
