package com.sml.service;

import java.util.List;

import com.sml.model.CommunityVO;
import com.sml.model.Criteria;
import com.sml.model.CommunityReplyDTO;

public interface CommunityService {
	
	public void communityEnroll(CommunityVO community)throws Exception;

	public List<CommunityVO> getBoardList(Criteria cri)throws Exception;
	
	public int communityGetTotal(Criteria cri) throws Exception;
	
	public CommunityVO communityDetail(int commCode) throws Exception;

	public int communityModify(CommunityVO community) throws Exception;
	
	public int communityDelete(int commCode);

	public CommunityVO getCommunityCode(int commCode);
	
	public int enrollReply(CommunityReplyDTO dto);
}
