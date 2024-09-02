package com.sml.service;

import java.util.List;

import com.sml.model.CommunityReplyVO;

public interface CommunityReplyService {
	
	public int enrollReply(CommunityReplyVO reply);
	
	public String checkReply(CommunityReplyVO reply);

}
