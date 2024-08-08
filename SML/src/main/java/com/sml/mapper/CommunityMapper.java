package com.sml.mapper;

import java.util.List;

import com.sml.model.CommunityVO;
import com.sml.model.Criteria;
import com.sml.model.ReplyDTO;

public interface CommunityMapper {
	
	public List<CommunityVO> getBoardList(Criteria cri);
	
	// 게시글 총 개수
	public int communityGetTotal(Criteria cri);
	
	public void communityEnroll (CommunityVO community);
	
	public CommunityVO communityDetail (int commCode);
	
	public int communityModify(CommunityVO community);
	
	public int communityDelete(int commCode);
	
	// title 검색
	// public List<CommunityVO> communityTitleSearch(Criteria cri);
	
}
