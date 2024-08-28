package com.sml.mapper;

import java.util.List;

import com.sml.model.CommunityReplyVO;
import com.sml.model.Criteria;

public interface CommunityReplyMapper {
	
	public List<CommunityReplyVO> getReplyList(Criteria cri);
	
	public int replyGetTotal(Criteria cri);
	
	public void enrollReply(CommunityReplyVO reply);
	
	public CommunityReplyVO getReply(int repCode);
	public void updateReply(CommunityReplyVO reply);
	
	public void deleteReply(int repCode);
	
}
