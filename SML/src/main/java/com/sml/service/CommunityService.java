package com.sml.service;

import java.util.List;

import com.sml.model.CommunityVO;
import com.sml.model.Criteria;
import com.sml.model.ReplyDTO;

public interface CommunityService {
	
	public void communityEnroll(CommunityVO community)throws Exception;

	public List<CommunityVO> getBoardList(Criteria cri)throws Exception;
	
	public int communityGetTotal(Criteria cri) throws Exception;
	
	public CommunityVO communityDetail(int commCode) throws Exception;

	public int communityModify(CommunityVO community) throws Exception;
	
	public int communityDelete(int commCode);
	
	
	//댓글
	public int enrollReply(ReplyDTO dto);	
	
	public List<ReplyDTO> listReply() throws Exception;
	
//	public int replyGetTotal(Criteria cri) throws Exception;
//	
//	public int replyModify(ReplyDTO dto) throws Exception;
//	
//	public int replyDelete(int repCode) throws Exception;
	
}
