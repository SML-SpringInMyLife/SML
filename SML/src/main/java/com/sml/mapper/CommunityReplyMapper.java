package com.sml.mapper;

import java.util.List;

import com.sml.model.CommunityReplyDTO;
import com.sml.model.Criteria;

public interface CommunityReplyMapper {
	
	public List<CommunityReplyDTO> getReplyList(Criteria cri);
	
	public int replyGetTotal(Criteria cri);

}
