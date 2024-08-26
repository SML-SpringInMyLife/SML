package com.sml.mapper;

import java.util.List;

import com.sml.model.Criteria;
import com.sml.model.CommunityReplyDTO;

public interface CommunityReplyMapper {
	
	public void enrollReply(CommunityReplyDTO reply);
	
	public int getSequence();
	
	public List<CommunityReplyDTO> replyList(CommunityReplyDTO reply);
	
	public void updateReply(CommunityReplyDTO reply);

	public void deleteReply(int repCode);
	
	public CommunityReplyDTO replyData(int repCode);
	
	public int countReply(int commGroup);
}
