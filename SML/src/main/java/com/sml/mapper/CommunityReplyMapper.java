package com.sml.mapper;

import java.util.List;

import com.sml.model.CommunityReplyVO;
import com.sml.model.Criteria;

public interface CommunityReplyMapper {
	
	public int enrollReply(CommunityReplyVO reply);
	
	public Integer checkReply(CommunityReplyVO reply);
	
}
