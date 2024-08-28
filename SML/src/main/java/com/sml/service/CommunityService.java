package com.sml.service;

import java.util.List;

import com.sml.model.CommunityReplyVO;
import com.sml.model.CommunityVO;
import com.sml.model.Criteria;
import com.sml.model.MemberVO;

public interface CommunityService {
	
	// 기본 CRUD
	public void communityEnroll(CommunityVO community) throws Exception;

	public List<CommunityVO> getBoardList(Criteria cri) throws Exception;
	
	public int communityGetTotal(Criteria cri) throws Exception;
	
	public CommunityVO communityDetail(int commCode) throws Exception;

	public int communityModify(CommunityVO community) throws Exception;
	
	public int communityDelete(int commCode);

	public CommunityVO getCommunityCode(int commCode);
	
	public int communityPoint(MemberVO member) throws Exception;
	
	/* 댓글 기능
	 * 등록
	 * 수정
	 * 삭제
	 * (포인트)
	 * */
	
	public List<CommunityReplyVO> getReplyList(Criteria cri) throws Exception;
	
	public int replyGetTotal(Criteria cri) throws Exception;
	
	public void enrollReply(CommunityReplyVO reply) throws Exception;
	
	

}
