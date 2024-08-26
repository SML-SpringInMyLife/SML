package com.sml.mapper;

import java.util.List;

import com.sml.model.CommunityVO;
import com.sml.model.Criteria;
import com.sml.model.MemberVO;

public interface CommunityMapper {
	
	public List<CommunityVO> getBoardList(Criteria cri);

	public int communityGetTotal(Criteria cri);
	
	public void communityEnroll (CommunityVO community);
	
	public CommunityVO communityDetail (int commCode);
	
	public int communityModify(CommunityVO community);
	
	public int communityDelete(int commCode);
	

	public CommunityVO getCommunityCode(int commCode);
	
	public int communityPoint(MemberVO member);
	
}
